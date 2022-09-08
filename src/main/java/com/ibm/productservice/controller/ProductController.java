package com.ibm.productservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.productservice.entity.Product;
import com.ibm.productservice.service.ProductService;

@RestController
@RequestMapping(value = "/")
public class ProductController {
	@Autowired
    ProductService productService;

    @PostMapping("/saveProduct")
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
   	    System.out.println("product"+product);
   	    String productResponse = productService.addProduct(product); 
   	    return ResponseEntity.status(HttpStatus.OK).body(productResponse);
   	}
    
    @GetMapping("/getProducts")
    public List<Product> getAllProducts() {
    	System.out.println("getAllProducts");
    	return productService.getAllProducts();
    }
    @PutMapping("/saveProduct")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
    	System.out.println("updateProduct");
    	String updateProductResponse = productService.addProduct(product);
    	return ResponseEntity.status(HttpStatus.OK).body(updateProductResponse);
    }
    
    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
    	System.out.println("getProduct");
    	Optional<Product> prod =  productService.getProduct(id);
    	return ResponseEntity.status(HttpStatus.OK).body(prod.get());
    }
}
