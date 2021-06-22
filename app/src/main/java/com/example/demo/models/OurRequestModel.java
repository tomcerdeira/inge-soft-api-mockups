package com.example.demo.models;


import com.example.demo.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "our_request")
public class OurRequestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private double latitudeInit;
    private double longitudeInit;

    private double latitudeDest;
    private double longitudeDest;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitudeInit() {
        return latitudeInit;
    }

    public void setLatitudeInit(double latitudeInit) {
        this.latitudeInit = latitudeInit;
    }

    public double getLongitudeInit() {
        return longitudeInit;
    }

    public void setLongitudeInit(double longitudeInit) {
        this.longitudeInit = longitudeInit;
    }

    public double getLatitudeDest() {
        return latitudeDest;
    }

    public void setLatitudeDest(double latitudeDest) {
        this.latitudeDest = latitudeDest;
    }

    public double getLongitudeDest() {
        return longitudeDest;
    }

    public void setLongitudeDest(double longitudeDest) {
        this.longitudeDest = longitudeDest;
    }


}
