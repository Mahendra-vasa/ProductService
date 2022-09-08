package com.ibm.productservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.productservice.dao.ProductRepository;
import com.ibm.productservice.entity.Product;

@Service
public class ProductService {
	 @Autowired
	  ProductRepository repository;
	  List<Product> employeeProfileList = new ArrayList < > ();

      public String addProduct(Product product) {
        repository.save(product);
        return "Product Created For: "+product.getId();
      }
      
      public List < Product > getAllProducts() {
          return repository.findAll();
      }
      
      public Optional< Product > getProduct(int id) {
    	  Optional< Product > product = repository.findById(id);
          return product;
      }
}
