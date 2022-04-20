package com.hashan.nagp.ordersservice.models.shared;


public class WorkerAssignNotificationEvent {
    private String workerId;
    private String type;
    private String assignedOrderId;


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
    public String getWorkerId() {
        return workerId;
    }
    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkerAssignNotificationEvent that = (WorkerAssignNotificationEvent) o;

        if (workerId != null ? !workerId.equals(that.workerId) : that.workerId != null)
            return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return assignedOrderId != null ? assignedOrderId.equals(that.assignedOrderId) : that.assignedOrderId == null;
    }


    @Override
    public String toString() {
        return "ServiceProviderAssignNotificationEvent{" +
                "workerId='" + workerId + '\'' +
                ", type='" + type + '\'' +
                ", assignedOrderId='" + assignedOrderId + '\'' +
                '}';
    }
}
