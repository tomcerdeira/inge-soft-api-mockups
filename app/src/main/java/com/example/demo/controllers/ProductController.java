package com.example.demo.controllers;

import com.example.demo.models.ProductModel;
import com.example.demo.services.ProductService;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/product/{api_id}") // lo agrego aca por consistencia
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping()
    public ArrayList<ProductModel> obtenerProductos(@PathVariable Integer api_id){
        return productService.obtenerProductos();
    }

    @PostMapping()
    public ProductModel guardarProducto(@RequestBody ProductModel producto,@PathVariable Integer api_id){
        return this.productService.guardarProducto(producto);
    }

    @GetMapping( path = "/{id}")
    public ProductModel obtenerProductoPorId(@PathVariable("id") Long id,@PathVariable Integer api_id) {
        return this.productService.obtenerPorId(id);
    }

    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id,@PathVariable Integer api_id){
        boolean ok = this.productService.eliminarProducto(id);
        if (ok){
            return "Se eliminó el producto con id " + id;
        }else{
            return "No pudo eliminar el producto con id " + id;
        }
    }
}
