package com.hashan.nagp.workerservice.models.shared;

import com.hashan.nagp.workerservice.models.ConsumerUserModel;


public class WorkerNotificationEvent {

    private String workerId;
    private String type;
    private String assignedOrderId;
    private String orderDescription;
    private ConsumerUserModel consumerInfo;


    public String getWorkerId() {
        return workerId;
    }
    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getAssignedOrderId() {
        return assignedOrderId;
    }
    public void setAssignedOrderId(String assignedOrderId) {
        this.assignedOrderId = assignedOrderId;
    }
    public String getOrderDescription() {
        return orderDescription;
    }
    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }
    public ConsumerUserModel getConsumerInfo() {
        return consumerInfo;
    }
    public void setConsumerInfo(ConsumerUserModel consumerInfo) {
        this.consumerInfo = consumerInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkerNotificationEvent that = (WorkerNotificationEvent) o;

        if (workerId != null ? !workerId.equals(that.workerId) : that.workerId != null)
            return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (assignedOrderId != null ? !assignedOrderId.equals(that.assignedOrderId) : that.assignedOrderId != null)
            return false;
        if (orderDescription != null ? !orderDescription.equals(that.orderDescription) : that.orderDescription != null)
            return false;
        return consumerInfo != null ? consumerInfo.equals(that.consumerInfo) : that.consumerInfo == null;
    }

    @Override
    public String toString() {
        return "WorkerNotificationEvent{" +
                "workerId='" + workerId + '\'' +
                ", type='" + type + '\'' +
                ", assignedOrderId='" + assignedOrderId + '\'' +
                ", orderDescription='" + orderDescription + '\'' +
                ", consumerInfo=" + consumerInfo +
                '}';
    }
}
