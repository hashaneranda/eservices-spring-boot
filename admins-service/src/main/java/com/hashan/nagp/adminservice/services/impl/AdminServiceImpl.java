package com.hashan.nagp.adminservice.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hashan.nagp.adminservice.exceptions.AssignOrder;
import com.hashan.nagp.adminservice.exceptions.UriBuilderException;
import com.hashan.nagp.adminservice.models.shared.OrderAssignNotificationEvent;
import com.hashan.nagp.adminservice.services.AdminService;
import com.hashan.nagp.adminservice.exceptions.ValidateParamsException;
import com.hashan.nagp.adminservice.models.OrderAssignmentModel;
import com.hashan.nagp.adminservice.util.UrlGenerator;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


@Service
public class AdminServiceImpl implements AdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Value("${worker-service.service-name}")
    private String workersServiceName;
    @Value("${worker-service-get-endpoint.path}")
    private String workersGetEndpointPath;
    @Value("${order-service.service-name}")
    private String ordersServiceName;
    @Value("${order-service.order-get-endpoint.path}")
    private String ordersGetEndpointPath;
    @Value("${kafka.order-notifications.topic}")
    private String orderNotificationsTopic;

    @Autowired
    private UrlGenerator serviceUriBuilder;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Override
    public void assignOrder(OrderAssignmentModel orderAssignmentModel) throws AssignOrder, ValidateParamsException {
        OrderAssignNotificationEvent event = new OrderAssignNotificationEvent();
        event.setOrderId(orderAssignmentModel.getOrderId());
        event.setWorkerId(orderAssignmentModel.getWorkerId());

        validateAssignmentReq(orderAssignmentModel);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            kafkaTemplate.send(orderNotificationsTopic, objectMapper.writeValueAsString(event));
            LOGGER.info("Submitted message for {} topic", orderNotificationsTopic);
        } catch (JsonProcessingException e) {
            throw new AssignOrder("Error occurred when sending order assignment event.", e);
        }
    }


    private void validateAssignmentReq(OrderAssignmentModel orderAssignmentModel) throws ValidateParamsException, AssignOrder {
        if (StringUtils.isEmpty(orderAssignmentModel.getWorkerId())) {
            throw new ValidateParamsException("Service id not found");
        }
        if (StringUtils.isEmpty(orderAssignmentModel.getOrderId())) {
            throw new ValidateParamsException("Consumer id not found");
        }

        URI serviceUri = null;

        try {
            serviceUri = serviceUriBuilder.generateUri(workersServiceName, workersGetEndpointPath + "/" + orderAssignmentModel.getWorkerId());
        } catch (UriBuilderException e) {
            throw new AssignOrder("Error occurred when generating the URI for services get endpoint.", e);
        }

        ResponseEntity<String> servicesResponseEntity = null;

        try {
            servicesResponseEntity = restTemplate.getForEntity(serviceUri, String.class);
        } catch (HttpClientErrorException e) {
            if (e.getRawStatusCode() == 404) {
                throw new ValidateParamsException("Service provider id is invalid.", e);
            }
            throw new AssignOrder("Error occurred when trying to connect to service provider service", e);
        }

        if (servicesResponseEntity.getStatusCode() != HttpStatus.OK) {
            throw new ValidateParamsException("Service id is invalid.");
        }

        URI ordersUri = null;
        try {
            ordersUri = serviceUriBuilder.generateUri(ordersServiceName, ordersGetEndpointPath + "/" + orderAssignmentModel.getOrderId());
        } catch (UriBuilderException e) {
            throw new AssignOrder("Error occurred when generating the URI for orders get endpoint.", e);
        }

        ResponseEntity<String> ordersResponseEntity = null;
        try {
            ordersResponseEntity = restTemplate.getForEntity(ordersUri, String.class);
        } catch (HttpClientErrorException e) {
            if (e.getRawStatusCode() == 404) {
                throw new ValidateParamsException("Order id is invalid.", e);
            }
            throw new AssignOrder("Error occurred when trying to connect to order service", e);
        }
        if (ordersResponseEntity.getStatusCode() != HttpStatus.OK) {
            throw new ValidateParamsException("Order id is invalid.");
        }
    }
}
