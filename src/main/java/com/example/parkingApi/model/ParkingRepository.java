package com.example.parkingApi.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParkingRepository extends CrudRepository<Parking, Long> {
    List<Parking> findById(String id);
}
