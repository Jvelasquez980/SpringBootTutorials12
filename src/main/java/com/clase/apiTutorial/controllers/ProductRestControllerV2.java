package com.clase.apiTutorial.controllers;


import java.util.List;

import java.util.stream.Collectors;


import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.clase.apiTutorial.DTOs.ProductDTO;

import com.clase.apiTutorial.models.Product;


@RestController

@RequestMapping("/api/v2")

public class ProductRestControllerV2 {


// Simulación de datos

private List<Product> products = List.of(

new Product(1L, "Producto A", 10.0),

new Product(2L, "Producto B", 20.0)

);


// Endpoint para listar productos con DTO

@GetMapping("/products")

public ResponseEntity<List<ProductDTO>> index() {

List<ProductDTO> dtos = products.stream()

.map(p -> new ProductDTO(p.getId(), p.getName(), p.getPrice()))

.collect(Collectors.toList());

return ResponseEntity.ok(dtos);

}


// Endpoint para mostrar producto por ID con DTO

@GetMapping("/products/{id}")

public ResponseEntity<ProductDTO> show(@PathVariable Long id) {

Product product = products.stream()

.filter(p -> p.getId().equals(id))

.findFirst()

.orElse(null);

if (product == null) {

return ResponseEntity.notFound().build();

}

ProductDTO dto = new ProductDTO(product.getId(), product.getName(), product.getPrice());

return ResponseEntity.ok(dto);

}

}