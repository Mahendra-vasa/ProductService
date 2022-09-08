package com.ibm.productservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.productservice.entity.Product;

public interface ProductRepository extends JpaRepository <Product , Integer> {
    
}
 
