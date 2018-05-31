package com.example.parkingApi.controller;

import com.example.parkingApi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
@RequestMapping("/")
public class MyRestController {

    private ParkingMeter parkingMeter;

    @Autowired
    public MyRestController(ParkingMeter parkingMeter) {
        this.parkingMeter = parkingMeter;
    }

    @RequestMapping(value = "/start/post", method = RequestMethod.POST)
    boolean startParking(@ModelAttribute("driver") Driver driver) {
        return parkingMeter.start(driver.getDriverType(), HtmlUtils.htmlEscape(driver.getId()));
    }

    @RequestMapping(value = "/stop/post", method = RequestMethod.POST)
    boolean stopParking(@ModelAttribute("driver") Driver driver) {
        return parkingMeter.stop(driver.getId());
    }

    @RequestMapping(value = "/cost/{driverId}", method = RequestMethod.GET, produces = "application/json")
    Cost getCost(@PathVariable("driverId") String id) {
        return parkingMeter.getCost(id);
    }

    /*
    *Method for additional insight
     */
    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json")
    Iterable<Parking> getAllParkings() {
        return parkingMeter.getAllParkings();
    }

    /*
     *Method for additional insight
     */
    @RequestMapping(value = "/get/all/{driverId}", method = RequestMethod.GET, produces = "application/json")
    List<Parking> getParkings(@PathVariable("driverId") String id) {
        return parkingMeter.getParkings(id);
    }
}
