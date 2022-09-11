package com.ibm.productservice.controller;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.ibm.productservice.constants.ApplicationConstants;
import com.ibm.productservice.entity.Product;
import com.ibm.productservice.exception.NoDataFoundException;
import com.ibm.productservice.exception.ProductNotFoundException;
import com.ibm.productservice.service.ProductService;
import com.netflix.discovery.shared.Application;

@RestController
@RequestMapping(value = "/")
public class ProductController {
	@Autowired
    ProductService productService;
	private static final Logger log = LoggerFactory.getLogger(ProductController.class);
	
    @PostMapping("/saveProduct")
    public ResponseEntity<String> saveProduct(@RequestBody @Valid Product product) throws Exception {
    	log.info(ApplicationConstants.PRODUCT_ID +product.getId());
   	    String productResponse = productService.addProduct(product); 
   	    return ResponseEntity.status(HttpStatus.OK).body(productResponse);
   	}
    
    @GetMapping("/getProducts")
    public List<Product> getAllProducts() throws NoDataFoundException {
    	log.info("getAllProducts Method in ProductController Class ");
    	return productService.getAllProducts();
    }
    @PutMapping("/saveProduct")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) throws Exception {
    	log.info(ApplicationConstants.PRODUCT_ID +product.getId());
    	String updateProductResponse = productService.addProduct(product);
    	return ResponseEntity.status(HttpStatus.OK).body(updateProductResponse);
    }
    
    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) throws ProductNotFoundException {
    	log.info(ApplicationConstants.PRODUCT_ID +id);
    	Optional<Product> prod =  productService.getProduct(id);
    	return ResponseEntity.status(HttpStatus.OK).body(prod.get());
    }
}
