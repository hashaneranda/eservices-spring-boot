package com.hashan.nagp.consumerservice.services.impl;

import com.hashan.nagp.consumerservice.models.ConsumerUserModel;
import com.hashan.nagp.consumerservice.services.ConsumerUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ConsumerUserServiceImpl implements ConsumerUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerUserServiceImpl.class);
    private static final Map<String, ConsumerUserModel> CONSUMER_USER_MAP = new HashMap<>();

    static {
        ConsumerUserModel consumerUserModel = new ConsumerUserModel();
        consumerUserModel.setId("C1");
        consumerUserModel.setName("Jane Doe");
        consumerUserModel.setEmail("jane@abc.com");
        consumerUserModel.setTelephone("111000546");
        consumerUserModel.setAddress("address");
        CONSUMER_USER_MAP.put(consumerUserModel.getId(), consumerUserModel);
    }

    public ConsumerUserServiceImpl() {
        ConsumerUserModel consumerUserModel = CONSUMER_USER_MAP.get("C1");
        LOGGER.info("Default User => ID: " + consumerUserModel.getId() + " Name: " + consumerUserModel.getName());
    }


    @Override
    public List<ConsumerUserModel> getAllConsumerUsers() {
        return new ArrayList<>(CONSUMER_USER_MAP.values());
    }

    @Override
    public ConsumerUserModel getConsumerUserById(String id) {
        return CONSUMER_USER_MAP.get(id);
    }

    @Override
    public ConsumerUserModel addConsumerUser(ConsumerUserModel consumerModel) {
        CONSUMER_USER_MAP.put(consumerModel.getId(), consumerModel);
        return consumerModel;
    }

    @Override
    public ConsumerUserModel updateConsumerUser(ConsumerUserModel consumerModel) {
        CONSUMER_USER_MAP.put(consumerModel.getId(), consumerModel);
        return consumerModel;
    }

    @Override
    public void deleteConsumerUser(String id) {
        CONSUMER_USER_MAP.remove(id);
    }
}
