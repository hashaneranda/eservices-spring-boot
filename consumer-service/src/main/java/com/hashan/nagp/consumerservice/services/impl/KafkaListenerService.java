package com.hashan.nagp.consumerservice.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hashan.nagp.consumerservice.models.ConsumerUserModel;
import com.hashan.nagp.consumerservice.models.shared.ConsumerNotificationEvent;
import com.hashan.nagp.consumerservice.models.shared.WorkerNotificationEvent;
import com.hashan.nagp.consumerservice.services.ConsumerUserService;
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
    private ConsumerUserService consumerUserService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.worker-notifications.topic}")
    private String serviceProviderNotificationsTopic;


    @KafkaListener(topics = "${kafka.consumer-notifications.topic}", groupId = "${kafka.config.consumer.group-id}")
    public void listenToOrderNotificationsTopic(String message) {
        LOGGER.info("Received kafka message: {}", message);

        try {
            ConsumerNotificationEvent consumerNotificationEvent = objectMapper.readValue(message, ConsumerNotificationEvent.class);

            if (consumerNotificationEvent.isOrderApproved()) {
                printNotification("Order id: " + consumerNotificationEvent.getOrderId() +
                        " Placed by consumer id: " + consumerNotificationEvent.getConsumerId() +
                        " has been approved. Worker info: " + consumerNotificationEvent.getWorkerInfo());

                ConsumerUserModel consumer = consumerUserService.getConsumerUserById(consumerNotificationEvent.getConsumerId());

                WorkerNotificationEvent serviceProviderNotificationEvent = new WorkerNotificationEvent();
                serviceProviderNotificationEvent.setWorkerId(consumerNotificationEvent.getWorkerInfo().getId());
                serviceProviderNotificationEvent.setAssignedOrderId(consumerNotificationEvent.getOrderId());
                serviceProviderNotificationEvent.setOrderDescription(consumerNotificationEvent.getOrderDescription());
                serviceProviderNotificationEvent.setConsumerInfo(consumer);
                serviceProviderNotificationEvent.setType("DetailedInfoNotification");

                kafkaTemplate.send(serviceProviderNotificationsTopic, objectMapper.writeValueAsString(serviceProviderNotificationEvent));
                LOGGER.info("Submitted detailed information to {} topic", serviceProviderNotificationsTopic);
            }

        } catch (JsonProcessingException e) {
            LOGGER.error("Error occurred when reading the consumer notification", e);
        }
    }

    private void printNotification(String notification) {
        System.out.println("\033[0;92m[RECEIVED_NOTIFICATION] " + notification);
    }
}
