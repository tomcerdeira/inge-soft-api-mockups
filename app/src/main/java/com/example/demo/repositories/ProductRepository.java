package com.example.demo.repositories;

import com.example.demo.models.ProductModel;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductModel, Long> {
//    public PriceDetailsModel findByPriceDetailsModelId(Long priceDetailsModelId);
}
