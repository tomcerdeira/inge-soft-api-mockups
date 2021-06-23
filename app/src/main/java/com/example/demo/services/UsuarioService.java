package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.ConstraintValueException;
import com.example.demo.InvalidIdException;
import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    public UsuarioModel guardarUsuario(UsuarioModel usuario){
        try {
            return usuarioRepository.save(usuario);
        }catch(Exception e){
            if(e.getMessage().contains("email")){
                throw new ConstraintValueException("El mail "+usuario.getEmail()+" ya se encuentra registrado");
            }else{
                throw new ConstraintValueException("El telefono "+usuario.getTelefono()+" ya se encuentra registrado");
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


    
}