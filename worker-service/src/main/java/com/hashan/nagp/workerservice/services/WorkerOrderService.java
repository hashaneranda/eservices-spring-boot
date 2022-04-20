package com.hashan.nagp.workerservice.services;

import com.hashan.nagp.workerservice.models.OrderStatusModel;


public interface WorkerOrderService {
    void setStatus(String workerId, OrderStatusModel orderStatusModel);
}
