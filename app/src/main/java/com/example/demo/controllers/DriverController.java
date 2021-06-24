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
@RequestMapping("/driver/{api_id}")
public class DriverController {
    @Autowired
    DriverService  driverService;

    @GetMapping()
    public ArrayList<DriverModel> obtenerTodosDriver(@PathVariable Integer api_id){
        return driverService.obtenerDrivers(api_id);
    }

    @PostMapping()
    public DriverModel guardarDriver(@RequestBody DriverModel driver,@PathVariable Integer api_id){
            return this.driverService.guardarDriver(driver,api_id);
    }

    @GetMapping( path = "/{driver_id}")
    public DriverModel obtenerDriverPorId(@PathVariable("driver_id") Long id,@PathVariable Integer api_id) throws InvalidIdException{
        return this.driverService.obtenerDriverPorId(id);
    }

    @PostMapping("{driver_id}/start_ride")
    public RequestModel startTrip(@PathVariable("driver_id") Long id,@PathVariable Integer api_id){
        DriverModel driverModel = obtenerDriverPorId(id,api_id);
        return this.driverService.startRide(driverModel.getCurrentTripId());
    }



    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.driverService.eliminarDriver(id);
        if (ok){
            return "Se eliminó el driver con id " + id;
        }else{
            return "No pudo eliminar el driver con id" + id;
        }
    }

}