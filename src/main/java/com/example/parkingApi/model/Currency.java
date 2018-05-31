package com.example.parkingApi.model;


public enum Currency {
    PLN(1.0);

    private double exchangeRate;

    Currency(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    double getExchangeRate() {
        return exchangeRate;
    }
}
