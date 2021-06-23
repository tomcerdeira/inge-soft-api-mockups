package com.example.demo.services;


import com.example.demo.models.RequestModel;
import com.example.demo.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestService {
    @Autowired
    RequestRepository requestRepository;

    public Optional<RequestModel> obtenerRequestPorId(Long id){
        return requestRepository.findById(id);
    }

    public RequestModel guardarRequest(RequestModel requestModel){return  requestRepository.save(requestModel);}



}
