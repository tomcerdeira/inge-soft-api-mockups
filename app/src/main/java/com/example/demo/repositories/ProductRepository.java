package com.example.demo.repositories;

import com.example.demo.models.ProductModel;
import com.example.demo.models.RequestModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductModel,Long> {

}