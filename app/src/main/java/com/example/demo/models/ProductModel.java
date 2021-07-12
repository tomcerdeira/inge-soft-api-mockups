package com.example.demo.models;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

// https://developer.uber.com/docs/riders/references/api/v1.2/products-get
//
// Ejemplo de POST
//
//{
//        "capacity": 4,
//        "product_id": 12345,
//        "service_fee": 2,
//        "cost_per_minute": 0.65,
//        "distance_unit": "mile",
//        "minimum": 15,
//        "cost_per_distance": 3.75,
//        "cancellation_fee": 10,
//        "cash_enabled": false,
//        "shared": false,
//        "short_description": "BLACK",
//        "display_name": "BLACK",
//        "description": "THE ORIGINAL UBER"
//        }

@Entity
@Table(name = "product")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Integer capacity;

    //private PriceDetailsModel price_details; //Deberia ser con esto, pero no podemos unir las tablas
    private Integer service_fee;
    private Integer cost_per_minute;
    private Integer minimum;
    private Integer cost_per_distance;
    private Integer cancellation_fee;
    private String currency_code;

    private boolean cash_enabled;
    private boolean shared;
    private String short_description;
    private String display_name;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public boolean isCash_enabled() {
        return cash_enabled;
    }

    public void setCash_enabled(boolean cash_enabled) {
        this.cash_enabled = cash_enabled;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
