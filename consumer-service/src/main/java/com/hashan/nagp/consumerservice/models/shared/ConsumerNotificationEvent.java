package com.hashan.nagp.consumerservice.models.shared;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class to represent the consumer notification event.
 */
public class ConsumerNotificationEvent {
    private String type;
    private boolean orderApproved;
    private String orderId;
    private String orderDescription;
    private WorkerInfo workerInfo;
    private String consumerId;


    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public WorkerInfo getWorkerInfo() {
        return workerInfo;
    }
    public void setWorkerInfo(WorkerInfo workerInfo) {
        this.workerInfo = workerInfo;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getOrderDescription() {
        return orderDescription;
    }
    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }
    public String getConsumerId() {
        return consumerId;
    }
    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }
    @JsonProperty("orderApproved")
    public boolean isOrderApproved() {
        return orderApproved;
    }

    @JsonProperty("orderApproved")
    public void setOrderApproved(boolean orderApproved) {
        this.orderApproved = orderApproved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConsumerNotificationEvent that = (ConsumerNotificationEvent) o;

        if (orderApproved != that.orderApproved) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (orderDescription != null ? !orderDescription.equals(that.orderDescription) : that.orderDescription != null)
            return false;
        if (workerInfo != null ? !workerInfo.equals(that.workerInfo) : that.workerInfo != null)
            return false;
        return consumerId != null ? consumerId.equals(that.consumerId) : that.consumerId == null;
    }



    @Override
    public String toString() {
        return "ConsumerNotificationEvent{" +
                "type='" + type + '\'' +
                ", orderApproved=" + orderApproved +
                ", orderId='" + orderId + '\'' +
                ", orderDescription='" + orderDescription + '\'' +
                ", workerInfo=" + workerInfo +
                ", consumerId='" + consumerId + '\'' +
                '}';
    }
}
