package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.ConstraintValueException;
import com.example.demo.InvalidIdException;
import com.example.demo.RequestStatus;
import com.example.demo.models.DriverModel;
import com.example.demo.models.ProductModel;
import com.example.demo.models.RequestModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.UsuarioRepository;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RequestService requestService;
    @Autowired
    OurRequestService ourRequestService;
    @Autowired
    DriverService driverService;
    @Autowired
    ProductService productService;

    
    public ArrayList<UsuarioModel> obtenerUsuarios(Integer api_id){
        ArrayList<UsuarioModel> usuarioModels= (ArrayList<UsuarioModel>) usuarioRepository.findAll();
        ArrayList<UsuarioModel> usuarioModels1 = new ArrayList<>();
        for(UsuarioModel usuarioModel:usuarioModels){
            if(api_id.equals(usuarioModel.getApi_id())){
                usuarioModels1.add(usuarioModel);
            }
        }
        return  usuarioModels1;
    }

    public UsuarioModel guardarUsuario(UsuarioModel usuario,Integer api_id){
        try {
            usuario.setApi_id(api_id);
            return usuarioRepository.save(usuario);
        }catch(Exception e){
            if(e.getMessage().contains("[uk_user_email];")){
                throw new ConstraintValueException("El mail "+usuario.getEmail()+" ya se encuentra registrado");
            }else if(e.getMessage().contains("[uk_user_telefono];")){
                throw new ConstraintValueException("El telefono "+usuario.getTelefono()+" ya se encuentra registrado");
            }else{
                throw new ConstraintValueException("El telefono "+usuario.getTelefono()+" y/o el mail"+usuario.getEmail()+" ya se encuentran registrados");
            }
        }
    }



    public UsuarioModel obtenerPorId(Long id){
        Optional<UsuarioModel> usuarioModel = usuarioRepository.findById(id);
        if(usuarioModel.isPresent()){
            return usuarioModel.get();
        }else{
            throw new InvalidIdException("UsrID: "+id+" no se encuenta registrado");
        }
    }


    public UsuarioModel obtenerPorTelefono(Integer telefono) {
        return usuarioRepository.findByTelefono(telefono);
    }

    public boolean eliminarUsuario(Long id) {
        try{
            usuarioRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    public double pagarSaldo(Long usr_id,Double toPay,Integer api_id){
       UsuarioModel user = obtenerPorId(usr_id);
           double currentSaldo= user.getCurrentSaldo();
           user.setCurrentSaldo(currentSaldo-toPay);
           guardarUsuario(user,api_id);
           return toPay;

    }

    public RequestModel getRequestedRideById(Long usr_id,Integer api_id){
    UsuarioModel user = obtenerPorId(usr_id);
            if(user.getCurrentTripId()!=null) {
                RequestModel requestModel = requestService.obtenerRequestPorId(user.getCurrentTripId());
                //todo ver si esto corresponde aca (depende si hacemos que el driver se mueva o no)
                double timeToPickUp = ourRequestService.getTimeOfPickUpEstimate(requestModel.getDriver_id(), requestModel.getInit_pos_lat(), requestModel.getInit_pos_long());
                double timeToarrive = ourRequestService.getTimeOfArrivalEstimate(requestModel.getDriver_id(), requestModel.getInit_pos_lat(), requestModel.getInit_pos_long(), requestModel.getDest_latitude(), requestModel.getDes_longitude());
                System.out.println("Current time - requestTime: " + (System.currentTimeMillis() - requestModel.getRequestTime()) + " | timeToPickUp: " + timeToPickUp);
                if (System.currentTimeMillis() - requestModel.getRequestTime() >= timeToPickUp) {
                    DriverModel driverModel = driverService.obtenerDriverPorId(requestModel.getDriver_id());
                    if (requestModel.getPickUpTime() == null) {
                        requestModel.setStatus(RequestStatus.DRIVER_ARRIVED_INITIAL.toString());
                        driverModel.setLatitude(requestModel.getInit_pos_lat());
                        driverModel.setLongitude(requestModel.getInit_pos_long());
                    } else if (System.currentTimeMillis() - requestModel.getPickUpTime() >= timeToarrive) {
                        ProductModel productModel = productService.obtenerPorId(driverModel.getProduct_id());
                        requestModel.setStatus(RequestStatus.DRIVER_ARRIVED_DESTINATION.toString());
                        requestModel.setTripCompletedTime(System.currentTimeMillis());
                        double distance = OurRequestService.calculateDistanceInMeters(requestModel.getInit_pos_lat(),requestModel.getInit_pos_long(),requestModel.getDest_latitude(),requestModel.getDes_longitude());
                        double priceToPay = distance * productModel.getCost_per_distance();
                        user.setCurrentSaldo(user.getCurrentSaldo()+priceToPay);
                        driverModel.setLatitude(requestModel.getDest_latitude());
                        driverModel.setLongitude(requestModel.getDes_longitude());
                        driverModel.setAvailable(true);
                        guardarUsuario(user,api_id);
                    } else {
                        requestModel.setStatus(RequestStatus.ON_TRIP.toString());
                    }
                    driverService.guardarDriver(driverModel,api_id);
                }
                requestService.guardarRequest(requestModel);
                return requestModel;
            }
            throw new InvalidIdException("El usuario con ID:"+usr_id+" no tiene viaje asociado");
    }

}