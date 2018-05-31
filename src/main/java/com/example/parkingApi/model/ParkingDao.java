package com.example.parkingApi.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ParkingDao {

    private ParkingRepository parkingRepository;

    @Autowired
    public ParkingDao(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    void addParking(Parking parking) {
        parkingRepository.save(parking);
    }

    void updateParking(Parking parking) {
        parkingRepository.save(parking);
    }

    Iterable<Parking> getAllParkings() {
        return parkingRepository.findAll();
    }

    Parking getLatestParking(String id) {
        List<Parking> parkings = parkingRepository.findById(id);
        return parkings.size() == 0 ? null : parkings.get(parkings.size() - 1);
    }

    List<Parking> getParkings(String id) {
        return parkingRepository.findById(id);
    }
}
