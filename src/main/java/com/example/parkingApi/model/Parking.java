package com.example.parkingApi.model;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "parkings")
public class Parking {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long index;
    @Column
    private String id;
    @Column
    private boolean isRunning = true;
    @Column
    private double cost = 0;
    @Column
    private LocalDateTime startTime;
    @Column
    private LocalDateTime endTime  = null;
    @Column
    private int duration = 0;
    @Column
    private DriverType driverType;
    @Column
    private Currency currency;

    Parking() {}

    public Parking(LocalDateTime startTime, DriverType driverType, String id) {
        this.driverType = driverType;
        this.startTime = startTime;
        this.id = id;
        this.currency = Currency.PLN;
    }

    void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    void setCost(double cost) {
        this.cost = cost;
    }

    void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    void setDuration(int duration) {
        this.duration = duration;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public double getCost() {
        return cost;
    }

    public int getDuration() {
        return duration;
    }

    void stop(LocalDateTime time) {
        isRunning = false;
        endTime = time;
        duration = (int) Math.ceil(Duration.between(startTime, endTime).getSeconds() / 60.0);
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    void changeCurrency(Currency newCurrency) {
        cost = cost * newCurrency.getExchangeRate() / currency.getExchangeRate();
        currency = newCurrency;
    }
}
