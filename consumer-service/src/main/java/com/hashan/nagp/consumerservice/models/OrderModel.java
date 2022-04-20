package com.hashan.nagp.consumerservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;


public class OrderModel {
    private String id;
    private String consumerId;
    private String utilityId;
    private String description;
    private String assignedWorkerId;
    private boolean isApproved;
    private LocalDateTime lastModifiedDateTime;


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
    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }
    public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getAssignedWorkerId() {
        return assignedWorkerId;
    }
    public void setAssignedWorkerId(String assignedWorkerId) {
        this.assignedWorkerId = assignedWorkerId;
    }

    @JsonProperty("isApproved")
    public boolean isApproved() {
        return isApproved;
    }

    @JsonProperty("isApproved")
    public void setApproved(boolean approved) {
        isApproved = approved;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderModel that = (OrderModel) o;

        if (isApproved != that.isApproved) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (consumerId != null ? !consumerId.equals(that.consumerId) : that.consumerId != null) return false;
        if (utilityId != null ? !utilityId.equals(that.utilityId) : that.utilityId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (assignedWorkerId != null ? !assignedWorkerId.equals(that.assignedWorkerId) : that.assignedWorkerId != null)
            return false;
        return lastModifiedDateTime != null ? lastModifiedDateTime.equals(that.lastModifiedDateTime) : that.lastModifiedDateTime == null;
    }


    @Override
    public String toString() {
        return "OrderModel{" +
                "id='" + id + '\'' +
                ", consumerId='" + consumerId + '\'' +
                ", utilityId='" + utilityId + '\'' +
                ", description='" + description + '\'' +
                ", assignedWorkerId='" + assignedWorkerId + '\'' +
                ", isApproved=" + isApproved +
                ", lastModifiedDateTime=" + lastModifiedDateTime +
                '}';
    }
}
