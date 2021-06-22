package com.example.demo.controllers;

import com.example.demo.models.DriverModel;
import com.example.demo.models.RequestModel;
import com.example.demo.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/requests")
public class RequestController {
    @Autowired
    RequestService requestService;




    @GetMapping( path = "/{id}")
    public Optional<RequestModel> obtenerRequestPorId(@PathVariable("id") Long id) {
        return this.requestService.obtenerRequestPorId(id);
    }
}
