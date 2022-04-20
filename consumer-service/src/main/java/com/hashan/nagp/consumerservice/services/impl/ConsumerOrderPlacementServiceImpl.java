package com.hashan.nagp.consumerservice.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hashan.nagp.consumerservice.exceptions.OrderPlacementException;
import com.hashan.nagp.consumerservice.exceptions.ServiceUriBuilderException;
import com.hashan.nagp.consumerservice.models.ConsumerOrderModel;
import com.hashan.nagp.consumerservice.models.OrderModel;
import com.hashan.nagp.consumerservice.models.OrderRequestBody;
import com.hashan.nagp.consumerservice.services.ConsumerOrderPlacementService;
import com.hashan.nagp.consumerservice.util.UrlGenerator;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

/**
 * Implementation class for the consumer order placement service.
 */
@Service
public class ConsumerOrderPlacementServiceImpl implements ConsumerOrderPlacementService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerUserServiceImpl.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UrlGenerator serviceUriBuilder;

    @Value("${orders-service.service-name}")
    private String ordersServiceName;

    @Value("${orders-service.orders-post-endpoint.path}")
    private String ordersServicePostEndpointPath;

    @Value("${utility-service.service-name}")
    private String utilityServiceName;

    @Value("${utility-service.orders-get-endpoint.path}")
    private String utilitiesGetEndpointPath;

    @Override
    public OrderModel placeOrder(String consumerId, ConsumerOrderModel consumerOrderModel) throws OrderPlacementException {
        URI uri = null;

        try {
            uri = serviceUriBuilder.generateUri(ordersServiceName, ordersServicePostEndpointPath);
        } catch (ServiceUriBuilderException e) {
            throw new OrderPlacementException("Error occurred when trying to build the URI", e);
        }

        OrderRequestBody reqBody = new OrderRequestBody();
        reqBody.setId(UUID.randomUUID().toString());
        reqBody.setConsumerId(consumerId);
        reqBody.setUtilityId(consumerOrderModel.getUtilityId());
        reqBody.setDescription(consumerOrderModel.getDescription());

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, reqBody, String.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new OrderPlacementException("Order service returned status: " + responseEntity.getStatusCode());
        }

        try {
            return objectMapper.readValue(responseEntity.getBody(), OrderModel.class);
        } catch (JsonProcessingException e) {
            throw new OrderPlacementException("Error occurred when trying to deserialize order information", e);
        }
    }

    @Override
    public boolean validateOrderUtility(ConsumerOrderModel consumerOrderModel) throws OrderPlacementException {
        URI uri = null;

        try {
            uri = serviceUriBuilder.generateUri(utilityServiceName, utilitiesGetEndpointPath + "/" + consumerOrderModel.getUtilityId());

            ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

            return (responseEntity.getStatusCode() == HttpStatus.OK);

        } catch (ServiceUriBuilderException e) {
            throw new OrderPlacementException("Error occurred when trying to build the URI", e);
        } catch (Exception e) {
            LOGGER.error("Error occurred when trying check for the service", e);
        }

        return false;
    }
}
