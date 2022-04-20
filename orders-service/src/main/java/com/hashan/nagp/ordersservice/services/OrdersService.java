package com.hashan.nagp.ordersservice.services;

import com.hashan.nagp.ordersservice.models.OrderModel;

import java.util.List;


public interface OrdersService {

    List<OrderModel> getAllOrders();
    OrderModel getOrderById(String id);
    OrderModel addOrder(OrderModel orderModel);
    OrderModel updateOrder(OrderModel orderModel);
    void deleteOrder(String id);
}
