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

    private Long requestTime;
    private Long pickUpTime;
    private Long tripCompletedTime;

    private Long driver_id;

    //Destination info
    private String dest_alias;
    private Double dest_latitude;
    private Double des_longitude;

    //Init position info
    private String init_pos_alias;
    private Double init_pos_lat;
    private Double init_pos_long;


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

    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }

    public Long getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(Long pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public Long getTripCompletedTime() {
        return tripCompletedTime;
    }

    public void setTripCompletedTime(Long tripCompletedTime) {
        this.tripCompletedTime = tripCompletedTime;
    }

    public Long getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Long driver_id) {
        this.driver_id = driver_id;
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

    public String getInit_pos_alias() {
        return init_pos_alias;
    }

    public void setInit_pos_alias(String init_pos_alias) {
        this.init_pos_alias = init_pos_alias;
    }

    public Double getInit_pos_lat() {
        return init_pos_lat;
    }

    public void setInit_pos_lat(Double init_pos_lat) {
        this.init_pos_lat = init_pos_lat;
    }

    public Double getInit_pos_long() {
        return init_pos_long;
    }

    public void setInit_pos_long(Double init_pos_long) {
        this.init_pos_long = init_pos_long;
    }
}
