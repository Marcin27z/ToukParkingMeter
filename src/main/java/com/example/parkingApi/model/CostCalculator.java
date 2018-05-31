package com.example.parkingApi.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

class CostCalculator {

    private final double timePeriod = 3600.0; //one hour

    Double calculateCost(Parking parking) {
        DriverType driverType = parking.getDriverType();
        if (driverType == null)
            return null;
        double currencyExchangeRate = parking.getCurrency().getExchangeRate();
        int duration = calculateDuration(parking);
        Double cost;
        if (duration <= 1) {
            cost = driverType.getFirstHour() * currencyExchangeRate;
        } else if (duration <= 2) {
            cost = (driverType.getFirstHour() + driverType.getSecondHour()) * currencyExchangeRate;
        } else {
            cost = (driverType.getFirstHour() + geometricSeriesSum(driverType.getSecondHour(), driverType.getNextHour(), duration - 1)) * currencyExchangeRate;
        }
        return round(cost, 2);
    }

    int calculateDuration(Parking parking) {
        return (int) Math.ceil((double)Duration.between(parking.getStartTime(), LocalDateTime.now()).getSeconds() / timePeriod);
    }

    private double geometricSeriesSum(double a, double q, int n) {
        double sum = 0;
        for (long i = 0; i < n; i++) {
            sum += a;
            a *= q;
        }
        return sum;
    }

    private double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();
        BigDecimal bigDecimal = new BigDecimal(Double.toString(value));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

}
