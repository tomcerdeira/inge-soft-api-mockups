package com.example.demo.models;

import javax.persistence.*;

// https://developer.uber.com/docs/riders/references/api/v1.2/products-get

@Entity
@Table(name = "product")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private boolean upfront_fare_enabled;
    private Integer capacity;
    private Long product_id;

    @ManyToOne
    private PriceDetailsModel price_details;

    private String image;
    private boolean cash_enabled;
    private boolean shared;
    private String short_description;
    private String display_name;
    private String product_group;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isUpfront_fare_enabled() {
        return upfront_fare_enabled;
    }

    public void setUpfront_fare_enabled(boolean upfront_fare_enabled) {
        this.upfront_fare_enabled = upfront_fare_enabled;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public PriceDetailsModel getPrice_details() {
        return price_details;
    }

    public void setPrice_details(PriceDetailsModel price_details) {
        this.price_details = price_details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getProduct_group() {
        return product_group;
    }

    public void setProduct_group(String product_group) {
        this.product_group = product_group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
