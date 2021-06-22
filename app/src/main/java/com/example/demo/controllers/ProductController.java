package com.example.demo.controllers;

import com.example.demo.models.ProductModel;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping()
    public ArrayList<ProductModel> obtenerProductos(){
        return productService.obtenerProductos();
    }

    @PostMapping()
    public ProductModel guardarUsuario(@RequestBody ProductModel producto){
        return this.productService.guardarProducto(producto);
    }

    @GetMapping( path = "/{id}")
    public Optional<ProductModel> obtenerProductoPorId(@PathVariable("id") Long id) {
        return this.productService.obtenerPorId(id);
    }

    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.productService.eliminarProducto(id);
        if (ok){
            return "Se eliminó el producto con id " + id;
        }else{
            return "No pudo eliminar el producto con id " + id;
        }
    }
}
