package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.RequestModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuario/{api_id}")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios(@PathVariable Integer api_id){
        return usuarioService.obtenerUsuarios(api_id);
    }

    @PostMapping()
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario,@PathVariable Integer api_id){
        return this.usuarioService.guardarUsuario(usuario,api_id);
    }

    @PostMapping("/pay/{usr_id}")
    public double pagarSaldo(@PathVariable("usr_id") Long usr_id,@RequestBody Double toPay,@PathVariable Integer api_id){
        return this.usuarioService.pagarSaldo(usr_id,toPay,api_id);
    }

    @GetMapping( path = "/{id}")
    public UsuarioModel obtenerUsuarioPorId(@PathVariable("id") Long id,@PathVariable Integer api_id) {
        return this.usuarioService.obtenerPorId(id,api_id);
    }

    @GetMapping("/query")
    public UsuarioModel obtenerUsuarioPorTelefono(@RequestParam("telefono") Integer telefono,@PathVariable Integer api_id){
        return this.usuarioService.obtenerPorTelefono(telefono);
    }

    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id,@PathVariable Integer api_id){
        boolean ok = this.usuarioService.eliminarUsuario(id);
        if (ok){
            return "Se eliminó el usuario con id " + id;
        }else{
            return "No pudo eliminar el usuario con id" + id;
        }
    }

    @GetMapping("/{usr_id}/ride")
    public RequestModel getRequestedRide(@PathVariable("usr_id") Long usr_id,@PathVariable Integer api_id){
        return this.usuarioService.getRequestedRideById(usr_id,api_id);
    }

}