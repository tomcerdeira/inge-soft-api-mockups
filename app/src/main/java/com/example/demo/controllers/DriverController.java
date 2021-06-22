package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import com.example.demo.models.DriverModel;

import com.example.demo.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    DriverService  driverService;

    @GetMapping()
    public ArrayList<DriverModel> obtenerDriver(){
        return driverService.obtenerDrivers();
    }

    @PostMapping()
    public DriverModel guardarDriver(@RequestBody DriverModel driver){
            return this.driverService.guardarDriver(driver);
    }

    @GetMapping( path = "/{id}")
    public Optional<DriverModel> obtenerDriverPorId(@PathVariable("id") Long id) {
        return this.driverService.obtenerDriverPorId(id);
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