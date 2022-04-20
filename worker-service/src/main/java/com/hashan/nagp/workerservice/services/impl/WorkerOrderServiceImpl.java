package com.hashan.nagp.workerservice.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hashan.nagp.workerservice.models.OrderStatusModel;
import com.hashan.nagp.workerservice.models.WorkerModel;
import com.hashan.nagp.workerservice.models.shared.OrderNotificationEvent;
import com.hashan.nagp.workerservice.models.shared.WorkerInfo;
import com.hashan.nagp.workerservice.services.WorkerOrderService;
import com.hashan.nagp.workerservice.services.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WorkerOrderServiceImpl implements WorkerOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerOrderServiceImpl.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.order-notifications.topic}")
    private String orderNotificationTopic;

    @Autowired
    private WorkerService serviceProvidersService;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void setStatus(String workerId, OrderStatusModel orderStatusModel) {
        WorkerModel serviceProvider = serviceProvidersService.getWorkerById(workerId);

        OrderNotificationEvent event = new OrderNotificationEvent();
        event.setType("OrderApproveNotification");
        event.setApproved(orderStatusModel.isApproved());
        event.setOrderId(orderStatusModel.getOrderId());
        event.setWorkerId(workerId);

        WorkerInfo serviceProviderInfo = new WorkerInfo();
        serviceProviderInfo.setId(serviceProvider.getId());
        serviceProviderInfo.setName(serviceProvider.getName());
        serviceProviderInfo.setUtilityId(serviceProvider.getUtilityId());
        serviceProviderInfo.setTelephone(serviceProvider.getTelephone());

        event.setWorkerInfo(serviceProviderInfo);

        try {
            kafkaTemplate.send(orderNotificationTopic, objectMapper.writeValueAsString(event));
            LOGGER.info("Submitted kafka message for order approval / deny notification. Order id: {}, Service provider: {}, approved: {}", orderStatusModel.getOrderId(), workerId, orderStatusModel.isApproved());
        } catch (JsonProcessingException e) {
            LOGGER.error("Error occurred when trying to send data to order topic", e);
        }
    }
}
