package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.RequestModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

    @PostMapping()
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario){
        return this.usuarioService.guardarUsuario(usuario);
    }

    @PostMapping("/pay/{usr_id}")
    public double pagarSaldo(@PathVariable("usr_id") Long usr_id,@RequestBody Double toPay){
        return this.usuarioService.pagarSaldo(usr_id,toPay);
    }

    @GetMapping( path = "/{id}")
    public UsuarioModel obtenerUsuarioPorId(@PathVariable("id") Long id) {
        return this.usuarioService.obtenerPorId(id);
    }

    @GetMapping("/query")
    public UsuarioModel obtenerUsuarioPorTelefono(@RequestParam("telefono") Integer telefono){
        return this.usuarioService.obtenerPorTelefono(telefono);
    }

    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.usuarioService.eliminarUsuario(id);
        if (ok){
            return "Se eliminĂ³ el usuario con id " + id;
        }else{
            return "No pudo eliminar el usuario con id" + id;
        }
    }

    @GetMapping("/{usr_id}/ride")
    public RequestModel getRequestedRide(@PathVariable("usr_id") Long usr_id){
        System.out.println("ID USER "+usr_id);
        return this.usuarioService.getRequestedRideById(usr_id);
    }

}