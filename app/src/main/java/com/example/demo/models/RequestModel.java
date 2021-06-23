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

    private boolean shared;
    //Driver info
    private Integer driver_telephone;
    private String driver_picture;
    private String driver_name;
    private Integer driver_rate;
    private Double driver_longitude;
    private Double driver_latitude;
    //Car info
    private String car_brand;
    private String car_model;
    private String car_plate;
    private String car_image;
    //Destination info
    private String dest_alias;
    private Double dest_latitude;
    private Double des_longitude;


    public RequestModel() {

    }

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



    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public Integer getDriver_telephone() {
        return driver_telephone;
    }

    public void setDriver_telephone(Integer driver_telephone) {
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

    public Double getDriver_longitude() {
        return driver_longitude;
    }

    public void setDriver_longitude(Double driver_longitude) {
        this.driver_longitude = driver_longitude;
    }

    public Double getDriver_latitude() {
        return driver_latitude;
    }

    public void setDriver_latitude(Double driver_latitude) {
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

    public Double getDest_latitude() {
        return dest_latitude;
    }

    public void setDest_latitude(Double dest_latitude) {
        this.dest_latitude = dest_latitude;
    }

    public Double getDes_longitude() {
        return des_longitude;
    }

    public void setDes_longitude(Double des_longitude) {
        this.des_longitude = des_longitude;
    }

}
