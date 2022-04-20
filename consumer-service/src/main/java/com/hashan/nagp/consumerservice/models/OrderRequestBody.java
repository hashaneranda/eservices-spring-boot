package com.hashan.nagp.consumerservice.models;


public class OrderRequestBody {
    private String id;
    private String consumerId;
    private String utilityId;
    private String description;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getConsumerId() {
        return consumerId;
    }
    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }
    public String getUtilityId() {
        return utilityId;
    }
    public void setUtilityId(String utilityId) {
        this.utilityId = utilityId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderRequestBody that = (OrderRequestBody) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (consumerId != null ? !consumerId.equals(that.consumerId) : that.consumerId != null) return false;
        if (utilityId != null ? !utilityId.equals(that.utilityId) : that.utilityId != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }


    @Override
    public String toString() {
        return "OrderRequestBody{" +
                "id='" + id + '\'' +
                ", consumerId='" + consumerId + '\'' +
                ", utilityId='" + utilityId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
