package com.example.parkingApi.model;

public class Cost {

    private Double value;
    private Currency currency;

    Cost(Double value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public Double getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }
}