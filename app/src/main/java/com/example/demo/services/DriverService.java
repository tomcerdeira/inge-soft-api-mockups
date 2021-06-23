package com.example.demo.services;

import com.example.demo.ConstraintValueException;
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
        try{

            return driverRepository.save(driver);
        }catch (Exception e){
            throw new ConstraintValueException("La patente "+driver.getPatente_auto()+" ya se encuentra registrada");
        }
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

    public ArrayList<DriverModel> obtenerConductoresLibres(){
        ArrayList<DriverModel> drivers = obtenerDrivers();
        ArrayList<DriverModel> aux = new ArrayList<>();

        for(DriverModel driver:drivers){
            if(driver.isAvailable()){
                aux.add(driver);
            }
        }
        return aux;
    }


}
