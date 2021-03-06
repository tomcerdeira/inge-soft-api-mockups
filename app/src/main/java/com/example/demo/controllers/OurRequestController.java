package com.example.demo.controllers;


import com.example.demo.models.DriverModel;
import com.example.demo.models.OurRequestModel;
import com.example.demo.models.RequestModel;
import com.example.demo.services.OurRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/our_requests")
public class OurRequestController {

    @Autowired
    OurRequestService requestService;

    @GetMapping("/availableDrivers")
    public List<DriverModel> availableDriver(@RequestParam("latitudeInit") Double latitudeInit,@RequestParam("longitudeInit") Double longitudeInit,@RequestParam("latitudeDest") Double latitudeDest,@RequestParam("longitudeDest") Double longitudeDest){ //
        return requestService.getAvailablesDrivers(latitudeInit,longitudeInit,latitudeDest,longitudeDest);
    }

    @GetMapping("/estimatePrice")
    public double getPriceEstimateOfDriverId(@RequestParam("driverId") Long driverId, @RequestParam("latitudeInit") Double latitudeInit,@RequestParam("longitudeInit") Double longitudeInit,@RequestParam("latitudeDest") Double latitudeDest,@RequestParam("longitudeDest") Double longitudeDest){ //
        return requestService.getPriceEstimate(driverId, latitudeInit,longitudeInit,latitudeDest,longitudeDest);
    }

    @GetMapping("/estimatePickUp")
    public long getTimeEstimateOfPickUpOfDriverId(@RequestParam("driverId") Long driverId, @RequestParam("latitudeInit") Double latitudeInit,@RequestParam("longitudeInit") Double longitudeInit){ //
        return requestService.getTimeOfPickUpEstimate(driverId, latitudeInit,longitudeInit);
    }

    @GetMapping("/estimateArrival")
    public long getTimeEstimateOfArrivalOfDriverId(@RequestParam("driverId") Long driverId, @RequestParam("latitudeInit") Double latitudeInit,@RequestParam("longitudeInit") Double longitudeInit,@RequestParam("latitudeDest") Double latitudeDest,@RequestParam("longitudeDest") Double longitudeDest){ //
        return requestService.getTimeOfArrivalEstimate(driverId, latitudeInit,longitudeInit,latitudeDest,longitudeDest);
    }

    @PostMapping("/ride")
    public RequestModel setNewRide(@RequestParam("userId") Long userId, @RequestParam("driverId") Long driverId, @RequestParam("latitudeInit") Double latitudeInit, @RequestParam("longitudeInit") Double longitudeInit, @RequestParam("latitudeDest") Double latitudeDest, @RequestParam("longitudeDest") Double longitudeDest){
        return requestService.setNewDrive(userId,driverId,latitudeInit,longitudeInit,latitudeDest,longitudeDest);
    }

    @PostMapping("{ride_id}/{user_id}/cancel_ride")
    public void cancelRide(@PathVariable("user_id") Long user_id,@PathVariable("ride_id") Long ride_id){
        System.out.println("LLEGO A CANCEL"+ride_id+user_id);
        requestService.cancelRide(user_id,ride_id);
    }

}
