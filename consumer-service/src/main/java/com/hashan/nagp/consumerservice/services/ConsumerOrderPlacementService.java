package com.hashan.nagp.consumerservice.services;

import com.hashan.nagp.consumerservice.exceptions.OrderPlacementException;
import com.hashan.nagp.consumerservice.models.ConsumerOrderModel;
import com.hashan.nagp.consumerservice.models.OrderModel;


public interface ConsumerOrderPlacementService {

    OrderModel placeOrder(String consumerId, ConsumerOrderModel consumerOrderModel) throws OrderPlacementException;
    boolean validateOrderUtility(ConsumerOrderModel consumerOrderModel) throws OrderPlacementException;
}
