package com.hashan.nagp.adminservice.models;


public class OrderAssignmentModel {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderAssignmentModel that = (OrderAssignmentModel) o;

        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        return workerId != null ? workerId.equals(that.workerId) : that.workerId == null;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (workerId != null ? workerId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderAssignmentModel{" +
                "orderId='" + orderId + '\'' +
                ", workerId='" + workerId + '\'' +
                '}';
    }
}
