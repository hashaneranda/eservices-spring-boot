package com.hashan.nagp.workerservice.models.shared;

import com.fasterxml.jackson.annotation.JsonProperty;


public class OrderNotificationEvent {
    private String type;
    private String orderId;
    private String workerId;
    private boolean isApproved;
    private WorkerInfo workerInfo;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getWorkerId() {
        return workerId;
    }
    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    @JsonProperty("isApproved")
    public boolean isApproved() {
        return isApproved;
    }

    @JsonProperty("isApproved")
    public void setApproved(boolean approved) {
        isApproved = approved;
    }


    public WorkerInfo getWorkerInfo() {
        return workerInfo;
    }
    public void setWorkerInfo(WorkerInfo workerInfo) {
        this.workerInfo = workerInfo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderNotificationEvent that = (OrderNotificationEvent) o;

        if (isApproved != that.isApproved) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (workerId != null ? !workerId.equals(that.workerId) : that.workerId != null)
            return false;
        return workerInfo != null ? workerInfo.equals(that.workerInfo) : that.workerInfo == null;
    }


    @Override
    public String toString() {
        return "OrderNotificationEvent{" +
                "type='" + type + '\'' +
                ", orderId='" + orderId + '\'' +
                ", workerId='" + workerId + '\'' +
                ", isApproved=" + isApproved +
                ", workerInfo=" + workerInfo +
                '}';
    }
}
