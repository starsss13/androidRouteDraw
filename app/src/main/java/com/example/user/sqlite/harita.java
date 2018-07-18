package com.example.user.sqlite;

public class harita {
    Integer id;



    String location;
    Double latitude;
    Double longitude;

    public harita() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public harita(Double latitude,String location, Double longitude) {
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
