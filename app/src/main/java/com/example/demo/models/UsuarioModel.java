package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "usuario", uniqueConstraints = {
                        @UniqueConstraint(name = "uk_user_email",columnNames = {"email"}),
                        @UniqueConstraint(name = "uk_user_telefono", columnNames = {"telefono"})
                        })
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String nombre;
    private String apellido;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private Integer telefono;
    private String contrasena;
    private double currentSaldo=0;
    private Long currentTripId;

    public Long getCurrentTripId() {
        return currentTripId;
    }

    public void setCurrentTripId(Long currentTripId) {
        this.currentTripId = currentTripId;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public double getCurrentSaldo() {
        return currentSaldo;
    }

    public void setCurrentSaldo(double currentSaldo) {
        this.currentSaldo = currentSaldo;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}