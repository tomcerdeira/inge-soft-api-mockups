package com.example.demo.controllers;


import com.example.demo.models.DriverModel;
import com.example.demo.models.OurRequestModel;
import com.example.demo.services.OurRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/our_requests")
public class OurRequestController {

    @Autowired
    OurRequestService requestService;

    @GetMapping("/query")
    public List<DriverModel> availableDriver(@RequestParam("latitudeInit") Double latitudeInit,@RequestParam("longitudeInit") Double longitudeInit,@RequestParam("latitudeDest") Double latitudeDest,@RequestParam("longitudeDest") Double longitudeDest){ //
        return requestService.getAvailablesDrivers(latitudeInit,longitudeInit,latitudeDest,longitudeDest);
    }

    

}
