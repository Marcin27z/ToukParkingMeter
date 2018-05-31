package com.example.parkingApi.model;

public enum DriverType {
    REGULAR(1, 2, 1.5),
    VIP(0, 2, 1.2);

    private int firstHour;
    private int secondHour;
    private double nextHour;

    DriverType(int firstHour, int secondHour, double nextHour) {
        this.firstHour = firstHour;
        this.secondHour = secondHour;
        this.nextHour = nextHour;
    }

    int getFirstHour() {
        return firstHour;
    }

    int getSecondHour() {
        return secondHour;
    }

    double getNextHour() {
        return nextHour;
    }
}
