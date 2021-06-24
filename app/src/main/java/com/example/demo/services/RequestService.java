package com.example.demo.services;


import com.example.demo.InvalidIdException;
import com.example.demo.models.RequestModel;
import com.example.demo.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestService {
    @Autowired
    RequestRepository requestRepository;

    public RequestModel obtenerRequestPorId(Long id){
        Optional<RequestModel> requestModel= requestRepository.findById(id);
        if(requestModel.isPresent()){
            return requestModel.get();
        }else{
            throw new InvalidIdException("RequestID "+id+" no se encuentra cargado en la base de datos");
        }
    }

    public RequestModel guardarRequest(RequestModel requestModel){return  requestRepository.save(requestModel);}

    public void borrarPorId(Long id){requestRepository.deleteById(id);}



}
