package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.example.demo.InvalidIdException;
import com.example.demo.models.DriverModel;

import com.example.demo.models.RequestModel;
import com.example.demo.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    DriverService  driverService;

    @GetMapping()
    public ArrayList<DriverModel> obtenerTodosDriver(){
        return driverService.obtenerDrivers();
    }

    @PostMapping()
    public DriverModel guardarDriver(@RequestBody DriverModel driver){
            return this.driverService.guardarDriver(driver);
    }

    @GetMapping( path = "/{driver_id}")
    public DriverModel obtenerDriverPorId(@PathVariable("driver_id") Long id) throws InvalidIdException{
        return this.driverService.obtenerDriverPorId(id);
    }

    @PostMapping("{driver_id}/start_ride")
    public RequestModel startTrip(@PathVariable("driver_id") Long id){
        DriverModel driverModel = obtenerDriverPorId(id);
        return this.driverService.startRide(driverModel.getCurrentTripId());
    }



    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.driverService.eliminarDriver(id);
        if (ok){
            return "Se eliminĂ³ el driver con id " + id;
        }else{
            return "No pudo eliminar el driver con id" + id;
        }
    }

}