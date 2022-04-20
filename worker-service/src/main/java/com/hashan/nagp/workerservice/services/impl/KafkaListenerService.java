package com.hashan.nagp.workerservice.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hashan.nagp.workerservice.models.shared.WorkerNotificationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class KafkaListenerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListenerService.class);
    private ObjectMapper objectMapper = new ObjectMapper();


    @KafkaListener(topics = "${kafka.worker-notifications.topic}", groupId = "${kafka.config.consumer.group-id}")
    public void listenToServiceProviderNotificationsTopic(String message) {
        LOGGER.info("Received kafka message: {}", message);

        try {
            WorkerNotificationEvent event = objectMapper.readValue(message, WorkerNotificationEvent.class);

            if (event.getType().equals("OrderAssignNotification")) {
                printNotification("Order id: " + event.getAssignedOrderId() + " has been assigned to service provider id: " + event.getWorkerId());
            } else if (event.getType().equals("DetailedInfoNotification")) {
                printNotification("Detailed information of order id: + " + event.getAssignedOrderId() + ": description: " + event.getOrderDescription() +
                        "worker id: " + event.getWorkerId() +
                        " consumer information: " + event.getConsumerInfo());
            }

        } catch (JsonProcessingException e) {
            LOGGER.error("Error occurred when deserializing event", e);
        }
    }


    private void printNotification(String notification) {
        System.out.println("\033[0;92m[RECEIVED_NOTIFICATION] " + notification);
    }
}
