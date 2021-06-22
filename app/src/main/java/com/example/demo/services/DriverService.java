package com.example.demo.services;

import com.example.demo.models.DriverModel;

import com.example.demo.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DriverService {
    @Autowired
    DriverRepository driverRepository;

    public ArrayList<DriverModel> obtenerDrivers(){
        return (ArrayList<DriverModel>) driverRepository.findAll();
    }

    public DriverModel guardarDriver(DriverModel driver){
        return driverRepository.save(driver);
    }

    public Optional<DriverModel> obtenerDriverPorId(Long id){
        return driverRepository.findById(id);
    }

    public boolean eliminarDriver(Long id) {
        try{
            driverRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

}
