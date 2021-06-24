package com.example.demo.controllers;

import com.example.demo.models.DriverModel;
import com.example.demo.models.RequestModel;
import com.example.demo.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/requests/{api_id}")
public class RequestController {

    @Autowired
    RequestService requestService;

    @GetMapping( path = "/{id}")
    public RequestModel obtenerRequestPorId(@PathVariable("id") Long id,@PathVariable Integer api_id) {
        return this.requestService.obtenerRequestPorId(id);
    }

    @PostMapping()
    public RequestModel guardarRequest(RequestModel requestModel,@PathVariable Integer api_id) {
        return this.requestService.guardarRequest(requestModel);
    }



}
