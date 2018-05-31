package com.example.parkingApi.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ParkingMeter {

    private ParkingDao parkingDao;
    private CostCalculator costCalculator;

    @Autowired
    public ParkingMeter(ParkingDao parkingDao) {
        this.parkingDao = parkingDao;
        costCalculator = new CostCalculator();
    }

    public boolean start(DriverType driverType, String id) {
        Parking lastParking = parkingDao.getLatestParking(id);
        if ((lastParking == null) || (!lastParking.getIsRunning())) {
            Parking parking = new Parking(LocalDateTime.now(), driverType, id);
            parkingDao.addParking(parking);
            return true;
        } else {
            return false;
        }
    }

    public boolean stop(String id) {
        Parking parking = parkingDao.getLatestParking(id);
        if((parking == null) || (!parking.getIsRunning())) {
            return false;
        } else {
            parking.stop(LocalDateTime.now());
            parking.setCost(costCalculator.calculateCost(parking));
            parking.setDuration(costCalculator.calculateDuration(parking));
            parkingDao.updateParking(parking);
            return true;
        }
    }

    public Cost getCost(String id) {
        Parking parking = parkingDao.getLatestParking(id);
        if (parking == null)
            return null;
        return new Cost(costCalculator.calculateCost(parking), parking.getCurrency());
    }

    public Iterable<Parking> getAllParkings() {
        return parkingDao.getAllParkings();
    }

    public List<Parking> getParkings(String id) {
        return parkingDao.getParkings(id);
    }
}
