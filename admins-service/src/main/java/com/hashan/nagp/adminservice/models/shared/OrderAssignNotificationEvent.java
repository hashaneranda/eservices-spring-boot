package com.hashan.nagp.adminservice.models.shared;


public class OrderAssignNotificationEvent {
    private String type = "OrderAssignNotification";
    private String orderId;
    private String workerId;


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
    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderAssignNotificationEvent event = (OrderAssignNotificationEvent) o;

        if (type != null ? !type.equals(event.type) : event.type != null) return false;
        if (orderId != null ? !orderId.equals(event.orderId) : event.orderId != null) return false;
        return workerId != null ? workerId.equals(event.workerId) : event.workerId == null;
    }

    @Override
    public String toString() {
        return "OrderAssignNotificationEvent{" +
                "type='" + type + '\'' +
                ", orderId='" + orderId + '\'' +
                ", workerId='" + workerId + '\'' +
                '}';
    }
}
