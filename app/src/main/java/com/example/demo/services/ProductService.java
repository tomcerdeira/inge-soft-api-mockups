package com.example.demo.services;

import com.example.demo.models.ProductModel;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ArrayList<ProductModel> obtenerProductos(){
        return (ArrayList<ProductModel>) productRepository.findAll();
    }

    public ProductModel guardarProducto(ProductModel producto){
        return productRepository.save(producto);
    }

    public Optional<ProductModel> obtenerPorId(Long id){
        return productRepository.findById(id);
    }


    public boolean eliminarProducto(Long id) {
        try{
            productRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}
