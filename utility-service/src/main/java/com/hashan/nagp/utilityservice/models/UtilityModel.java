package com.hashan.nagp.utilityservice.models;


public class UtilityModel {
    private String id;
    private String name;

    public UtilityModel(String id, String name) {
        this.id = id;
        this.name = name;
    }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UtilityModel that = (UtilityModel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }

        return name != null ? name.equals(that.name) : that.name == null;
    }



    @Override
    public String toString() {
        return "UtilityModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
