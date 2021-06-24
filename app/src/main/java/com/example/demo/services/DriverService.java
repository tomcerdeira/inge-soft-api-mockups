package com.example.demo.services;

import com.example.demo.ConstraintValueException;
import com.example.demo.InvalidIdException;
import com.example.demo.RequestStatus;
import com.example.demo.models.DriverModel;

import com.example.demo.models.RequestModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DriverService {
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    RequestService requestService;

    public ArrayList<DriverModel> obtenerDrivers(Integer api_id){
        ArrayList<DriverModel> driverModels= (ArrayList<DriverModel>) driverRepository.findAll();
        ArrayList<DriverModel> driverModels1 = new ArrayList<>();
        for(DriverModel driverModel:driverModels){
            if(api_id.equals(driverModel.getApi_id())){
                driverModels1.add(driverModel);
            }
        }
        return  driverModels1;
    }

    public DriverModel guardarDriver(DriverModel driver,Integer api_id){
        //try{
            driver.setApi_id(api_id);
            return driverRepository.save(driver);
//        }catch (Exception e){
//            throw new ConstraintValueException("La patente "+driver.getPatente_auto()+" ya se encuentra registrada");
//        }
    }

    public DriverModel obtenerDriverPorId(Long id) throws InvalidIdException{
        Optional<DriverModel> driverModel = driverRepository.findById(id);
        if(driverModel.isPresent()){
            return driverModel.get();
        }else{
            throw new InvalidIdException("ID: "+id+" no se encuentra registrado en la base de datos");
        }
    }

    public boolean eliminarDriver(Long id) {
        try{
            driverRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    public ArrayList<DriverModel> obtenerConductoresLibres(Integer api_id){
        ArrayList<DriverModel> drivers = obtenerDrivers(api_id);
        ArrayList<DriverModel> aux = new ArrayList<>();

        for(DriverModel driver:drivers){
            if(driver.isAvailable()){
                aux.add(driver);
            }
        }
        return aux;
    }

    public RequestModel startRide(Long tripId){
        RequestModel requestModel =  requestService.obtenerRequestPorId(tripId);
        requestModel.setPickUpTime(System.currentTimeMillis());
        requestModel.setStatus(RequestStatus.ON_TRIP.toString());
        requestService.guardarRequest(requestModel);
        return requestModel;
    }


}
