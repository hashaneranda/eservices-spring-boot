package com.hashan.nagp.workerservice.models;


public class WorkerModel {
    private String id;
    private String name;
    private String utilityId;
    private String telephone;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUtilityId() {
        return utilityId;
    }
    public void setUtilityId(String utilityId) {
        this.utilityId = utilityId;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkerModel that = (WorkerModel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (utilityId != null ? !utilityId.equals(that.utilityId) : that.utilityId != null) return false;
        return telephone != null ? telephone.equals(that.telephone) : that.telephone == null;
    }


    @Override
    public String toString() {
        return "WorkerModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", utilityId='" + utilityId + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
