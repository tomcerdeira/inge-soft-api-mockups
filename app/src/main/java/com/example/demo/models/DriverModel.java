package com.example.demo.models;

import javax.persistence.*;
//https://developer.uber.com/docs/riders/references/api/v1.2/requests-current-get
@Entity
@Table(name = "driver")
public class DriverModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column(unique = false, nullable = false)
    private String nombre;

    private String apellido;
    @Column(unique = false, nullable = false)
    private String marca_auto;
    @Column(unique = true, nullable = false)
    private String patente_auto;
    private String imagen_auto;
    private String imagen_conductor;
    private Integer telefono;
    @Column(columnDefinition = "Integer default 0")
    private Integer rate=0;
    @Column(columnDefinition = "Integer default 0")
    private Double latitude= (double)0;
    @Column(columnDefinition = "Integer default 0")
    private Double longitude=(double)0;
    // private Integer tarjetaId


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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMarca_auto() {
        return marca_auto;
    }

    public void setMarca_auto(String marca_auto) {
        this.marca_auto = marca_auto;
    }

    public String getPatente_auto() {
        return patente_auto;
    }

    public void setPatente_auto(String patente_auto) {
        this.patente_auto = patente_auto;
    }

    public String getImagen_auto() {
        return imagen_auto;
    }

    public void setImagen_auto(String imagen_auto) {
        this.imagen_auto = imagen_auto;
    }

    public String getImagen_conductor() {
        return imagen_conductor;
    }

    public void setImagen_conductor(String imagen_conductor) {
        this.imagen_conductor = imagen_conductor;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
