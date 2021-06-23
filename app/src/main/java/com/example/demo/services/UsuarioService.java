package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.ConstraintValueException;
import com.example.demo.InvalidIdException;
import com.example.demo.RequestStatus;
import com.example.demo.models.DriverModel;
import com.example.demo.models.RequestModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.UsuarioRepository;

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

    
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    public UsuarioModel guardarUsuario(UsuarioModel usuario){
        try {
            return usuarioRepository.save(usuario);
        }catch(Exception e){
            System.out.println(e.getMessage()); // todo borrar esta linea
            if(e.getMessage().contains("[uk_user_email];")){
                throw new ConstraintValueException("El mail "+usuario.getEmail()+" ya se encuentra registrado");
            }else if(e.getMessage().contains("[uk_user_telefono];")){
                throw new ConstraintValueException("El telefono "+usuario.getTelefono()+" ya se encuentra registrado");
            }else{
                throw new ConstraintValueException("El telefono "+usuario.getTelefono()+" y/o el mail"+usuario.getEmail()+" ya se encuentran registrados");
            }
        }
    }



    public Optional<UsuarioModel> obtenerPorId(Long id){
        return usuarioRepository.findById(id);
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

    public double pagarSaldo(Long usr_id,Double toPay){
        Optional<UsuarioModel> user = obtenerPorId(usr_id);

        if(user.isPresent()){
           double currentSaldo= user.get().getCurrentSaldo();
           user.get().setCurrentSaldo(currentSaldo-toPay);
           guardarUsuario(user.get());
           return toPay;
        }else{
            throw new InvalidIdException("ID: "+usr_id+" no se encuentra en la base de datos");
        }
    }

    public RequestModel getRequestedRideById(Long usr_id){
        Optional<UsuarioModel> user = obtenerPorId(usr_id);
        if(user.isPresent()){
            Optional<RequestModel> requestModel = requestService.obtenerRequestPorId(user.get().getCurrentTripId());
            if(requestModel.isPresent()){
                //todo ver si esto corresponde aca (depende si hacemos que el driver se mueva o no)
                double timeToPickUp = ourRequestService.getTimeOfPickUpEstimate(requestModel.get().getDriver_id(),requestModel.get().getInit_pos_lat(),requestModel.get().getInit_pos_long());
                if(System.currentTimeMillis() - requestModel.get().getRequestTime() <=timeToPickUp) {
                    if (requestModel.get().getPickUpTime() == null && requestModel.get().getStatus().equals(RequestStatus.WAITING_FOR_PICK_UP.toString())) {
                        requestModel.get().setStatus(RequestStatus.DRIVER_ARRIVED_INITIAL.toString());
                        Optional<DriverModel> driverModel = driverService.obtenerDriverPorId(requestModel.get().getDriver_id());
                        driverModel.get().setLatitude(requestModel.get().getInit_pos_lat());
                        driverModel.get().setLongitude(requestModel.get().getInit_pos_long());
                        driverService.guardarDriver(driverModel.get());
                    } else {
                        requestModel.get().setStatus(RequestStatus.ON_TRIP.toString());
                    }
                }
                requestService.guardarRequest(requestModel.get());
                return  requestModel.get();
            }else{
                throw new InvalidIdException("TripID: "+user.get().getCurrentTripId()+" no se encuentra en la base de datos");
            }
        }else{
            throw new InvalidIdException("ID: "+usr_id+" no se encuentra en la base de datos");
        }
    }


    
}