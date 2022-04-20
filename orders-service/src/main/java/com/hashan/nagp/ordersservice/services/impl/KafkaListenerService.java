package com.hashan.nagp.ordersservice.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hashan.nagp.ordersservice.models.OrderModel;
import com.hashan.nagp.ordersservice.models.shared.ConsumerNotificationEvent;
import com.hashan.nagp.ordersservice.models.shared.OrderNotificationEvent;
import com.hashan.nagp.ordersservice.models.shared.WorkerAssignNotificationEvent;
import com.hashan.nagp.ordersservice.services.OrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class KafkaListenerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListenerService.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.worker-notifications.topic}")
    private String serviceProviderNotificationsTopic;

    @Value("${kafka.consumer-notifications.topic}")
    private String consumerNotificationsTopic;


    @KafkaListener(topics = "${kafka.order-notifications.topic}", groupId = "${kafka.config.consumer.group-id}")
    public void listenToOrderNotificationsTopic(String message) {
        LOGGER.info("Received kafka message: {}", message);

        try {
            OrderNotificationEvent orderNotificationEvent = objectMapper.readValue(message, OrderNotificationEvent.class);

            if (orderNotificationEvent.getType().equals("OrderAssignNotification")) {
                handleOrderAssignNotification(orderNotificationEvent);
            } else if (orderNotificationEvent.getType().equals("OrderApproveNotification")) {
                handleOrderApproveNotification(orderNotificationEvent);
            }

        } catch (JsonProcessingException e) {
            LOGGER.error("Error occurred when processing message", e);
        }
    }

    private void handleOrderAssignNotification(OrderNotificationEvent orderNotificationEvent) {
        printNotification("Order id: " + orderNotificationEvent.getOrderId() +
                " has been assigned to worker id: " + orderNotificationEvent.getWorkerId());
        OrderModel order = ordersService.getOrderById(orderNotificationEvent.getOrderId());
        order.setAssignedWorkerId(orderNotificationEvent.getWorkerId());
        ordersService.updateOrder(order);

        WorkerAssignNotificationEvent serviceProviderAssignNotificationEvent = new WorkerAssignNotificationEvent();
        serviceProviderAssignNotificationEvent.setType("OrderAssignNotification");
        serviceProviderAssignNotificationEvent.setAssignedOrderId(orderNotificationEvent.getOrderId());
        serviceProviderAssignNotificationEvent.setWorkerId(order.getAssignedWorkerId());

        try {
            kafkaTemplate.send(serviceProviderNotificationsTopic, objectMapper.writeValueAsString(serviceProviderAssignNotificationEvent));
            LOGGER.info("Submitted order assigned message to {} topic", serviceProviderNotificationsTopic);
        } catch (Exception e) {
            LOGGER.error("Error occurred when trying to send data to service provider topic", e);
        }
    }

    private void handleOrderApproveNotification(OrderNotificationEvent orderNotificationEvent) {
        printNotification("Order id: " + orderNotificationEvent.getOrderId() +
                " has been " + (orderNotificationEvent.isApproved() ? "approved" : "denied") +
                " by worker id: " + orderNotificationEvent.getWorkerInfo().getId());
        OrderModel order = ordersService.getOrderById(orderNotificationEvent.getOrderId());
        order.setApproved(orderNotificationEvent.isApproved());

        if (!orderNotificationEvent.isApproved()) {
            order.setAssignedWorkerId(null);
        }

        ordersService.updateOrder(order);

        if (orderNotificationEvent.isApproved()) {
            ConsumerNotificationEvent consumerNotificationEvent = new ConsumerNotificationEvent();
            consumerNotificationEvent.setType("OrderApprovedNotification");
            consumerNotificationEvent.setOrderApproved(true);
            consumerNotificationEvent.setWorkerInfo(orderNotificationEvent.getWorkerInfo());
            consumerNotificationEvent.setOrderId(orderNotificationEvent.getOrderId());
            consumerNotificationEvent.setOrderDescription(order.getDescription());
            consumerNotificationEvent.setConsumerId(order.getConsumerId());

            try {
                kafkaTemplate.send(consumerNotificationsTopic, objectMapper.writeValueAsString(consumerNotificationEvent));
                LOGGER.info("Submitted order approval message to {} topic", consumerNotificationsTopic);
            } catch (JsonProcessingException e) {
                LOGGER.error("Error occurred when trying to send data to consumer topic", e);
            }
        }
    }

    private void printNotification(String notification) {
        System.out.println("\033[0;92m[RECEIVED_NOTIFICATION] " + notification);
    }
}
