package com.example.parkingApi.model;

public class Driver {

    private String id;
    private DriverType driverType;

    public Driver() {}

    public void setId(String id) {
        this.id = id;
    }

    public void setDriverType(String driverType) {
        if (!driverType.equals("VIP") && !driverType.equals("vip"))
            this.driverType = DriverType.REGULAR;
        else
            this.driverType = DriverType.VIP;
    }

    public String getId() {
        return id;
    }

    public DriverType getDriverType() {
        return driverType;
    }
}