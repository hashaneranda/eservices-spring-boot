package com.hashan.nagp.consumerservice.models;

public class ConsumerOrderModel {
    private String utilityId;
    private String description;


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

        ConsumerOrderModel that = (ConsumerOrderModel) o;

        if (utilityId != null ? !utilityId.equals(that.utilityId) : that.utilityId != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }



    @Override
    public String toString() {
        return "ConsumerOrderModel{" +
                "utilityId='" + utilityId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
