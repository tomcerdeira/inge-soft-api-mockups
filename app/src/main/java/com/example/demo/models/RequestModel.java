package com.example.demo.models;

import javax.persistence.*;


//https://developer.uber.com/docs/riders/references/api/v1.2/requests-request_id-get

@Entity
@Table(name = "request")
public class RequestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Long product_id;
    private String status;
    private double surge_multiplier;
    private boolean shared;
    //Driver info
    private String driver_telephone;
    private String driver_picture;
    private String driver_name;
    private Integer driver_rate;
    private Integer driver_longitude;
    private Integer driver_latitude;
    //Car info
    private String car_brand;
    private String car_model;
    private String car_plate;
    private String car_image;
    //Destination info
    private String dest_alias;
    private Integer dest_latitude;
    private Integer des_longitude;
    private String dest_name;
    private String dest_addr;
    private Integer dest_eta;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getSurge_multiplier() {
        return surge_multiplier;
    }

    public void setSurge_multiplier(double surge_multiplier) {
        this.surge_multiplier = surge_multiplier;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public String getDriver_telephone() {
        return driver_telephone;
    }

    public void setDriver_telephone(String driver_telephone) {
        this.driver_telephone = driver_telephone;
    }

    public String getDriver_picture() {
        return driver_picture;
    }

    public void setDriver_picture(String driver_picture) {
        this.driver_picture = driver_picture;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public Integer getDriver_rate() {
        return driver_rate;
    }

    public void setDriver_rate(Integer driver_rate) {
        this.driver_rate = driver_rate;
    }

    public Integer getDriver_longitude() {
        return driver_longitude;
    }

    public void setDriver_longitude(Integer driver_longitude) {
        this.driver_longitude = driver_longitude;
    }

    public Integer getDriver_latitude() {
        return driver_latitude;
    }

    public void setDriver_latitude(Integer driver_latitude) {
        this.driver_latitude = driver_latitude;
    }

    public String getCar_brand() {
        return car_brand;
    }

    public void setCar_brand(String car_brand) {
        this.car_brand = car_brand;
    }

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }

    public String getCar_plate() {
        return car_plate;
    }

    public void setCar_plate(String car_plate) {
        this.car_plate = car_plate;
    }

    public String getCar_image() {
        return car_image;
    }

    public void setCar_image(String car_image) {
        this.car_image = car_image;
    }

    public String getDest_alias() {
        return dest_alias;
    }

    public void setDest_alias(String dest_alias) {
        this.dest_alias = dest_alias;
    }

    public Integer getDest_latitude() {
        return dest_latitude;
    }

    public void setDest_latitude(Integer dest_latitude) {
        this.dest_latitude = dest_latitude;
    }

    public Integer getDes_longitude() {
        return des_longitude;
    }

    public void setDes_longitude(Integer des_longitude) {
        this.des_longitude = des_longitude;
    }

    public String getDest_name() {
        return dest_name;
    }

    public void setDest_name(String dest_name) {
        this.dest_name = dest_name;
    }

    public String getDest_addr() {
        return dest_addr;
    }

    public void setDest_addr(String dest_addr) {
        this.dest_addr = dest_addr;
    }

    public Integer getDest_eta() {
        return dest_eta;
    }

    public void setDest_eta(Integer dest_eta) {
        this.dest_eta = dest_eta;
    }
}
