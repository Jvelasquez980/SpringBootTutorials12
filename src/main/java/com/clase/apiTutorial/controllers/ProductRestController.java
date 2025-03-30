package com.clase.apiTutorial.controllers;


import java.util.ArrayList;

import java.util.List;


import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.clase.apiTutorial.models.Product;;


@RestController

@RequestMapping("/api")

public class ProductRestController {


// Simulación de datos en memoria

private List<Product> products = new ArrayList<>();


public ProductRestController() {

// Productos de ejemplo

products.add(new Product(1L, "Producto A", 10.0));

products.add(new Product(2L, "Producto B", 20.0));

}


// Endpoint para listar todos los productos

@GetMapping("/products")

public ResponseEntity<List<Product>> index() {

return ResponseEntity.ok(products);

}


// Endpoint para mostrar un producto por ID

@GetMapping("/products/{id}")

public ResponseEntity<Product> show(@PathVariable Long id) {

Product product = products.stream()

.filter(p -> p.getId().equals(id))

.findFirst()

.orElse(null);

if (product == null) {

return ResponseEntity.notFound().build();

}

return ResponseEntity.ok(product);

}

}