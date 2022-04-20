package com.hashan.nagp.consumerservice.services;

import com.hashan.nagp.consumerservice.models.ConsumerUserModel;

import java.util.List;


public interface ConsumerUserService {

    List<ConsumerUserModel> getAllConsumerUsers();
    ConsumerUserModel getConsumerUserById(String id);
    ConsumerUserModel addConsumerUser(ConsumerUserModel consumerModel);
    ConsumerUserModel updateConsumerUser(ConsumerUserModel consumerModel);
    void deleteConsumerUser(String id);
}
