package com.hashan.nagp.ordersservice.services.impl;

import com.hashan.nagp.ordersservice.models.OrderModel;
import com.hashan.nagp.ordersservice.services.OrdersService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class OrdersServiceImpl implements OrdersService {
    private static final Map<String, OrderModel> ORDERS_MAP = new HashMap<>();


    @Override
    public List<OrderModel> getAllOrders() {
        return new ArrayList<>(ORDERS_MAP.values());
    }

    @Override
    public OrderModel getOrderById(String id) {
        return ORDERS_MAP.get(id);
    }

    @Override
    public OrderModel addOrder(OrderModel orderModel) {
        orderModel.setLastModifiedDateTime(LocalDateTime.now());
        ORDERS_MAP.put(orderModel.getId(), orderModel);
        return ORDERS_MAP.get(orderModel.getId());
    }

    @Override
    public OrderModel updateOrder(OrderModel orderModel) {
        orderModel.setLastModifiedDateTime(LocalDateTime.now());
        ORDERS_MAP.put(orderModel.getId(), orderModel);
        return ORDERS_MAP.get(orderModel.getId());
    }

    @Override
    public void deleteOrder(String id) {
        ORDERS_MAP.remove(id);
    }
}
