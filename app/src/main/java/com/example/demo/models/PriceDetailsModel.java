package com.example.demo.models;

import javax.persistence.*;

// https://developer.uber.com/docs/riders/references/api/v1.2/products-get

@Entity
@Table(name = "price_details")
public class PriceDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Integer service_fee;
    private Integer cost_per_minute;
    private String distance_unit;
    private Integer minimum;
    private Integer cost_per_distance;
    private Integer base;
    private Integer cancellation_fee;
    private String currency_code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getService_fee() {
        return service_fee;
    }

    public void setService_fee(Integer service_fee) {
        this.service_fee = service_fee;
    }

    public Integer getCost_per_minute() {
        return cost_per_minute;
    }

    public void setCost_per_minute(Integer cost_per_minute) {
        this.cost_per_minute = cost_per_minute;
    }

    public String getDistance_unit() {
        return distance_unit;
    }

    public void setDistance_unit(String distance_unit) {
        this.distance_unit = distance_unit;
    }

    public Integer getMinimum() {
        return minimum;
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    public Integer getCost_per_distance() {
        return cost_per_distance;
    }

    public void setCost_per_distance(Integer cost_per_distance) {
        this.cost_per_distance = cost_per_distance;
    }

    public Integer getBase() {
        return base;
    }

    public void setBase(Integer base) {
        this.base = base;
    }

    public Integer getCancellation_fee() {
        return cancellation_fee;
    }

    public void setCancellation_fee(Integer cancellation_fee) {
        this.cancellation_fee = cancellation_fee;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }
}
