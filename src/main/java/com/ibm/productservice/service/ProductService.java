package com.ibm.productservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.productservice.constants.ApplicationConstants;
import com.ibm.productservice.dao.ProductRepository;
import com.ibm.productservice.entity.Product;
import com.ibm.productservice.exception.NoDataFoundException;
import com.ibm.productservice.exception.ProductNotFoundException;

@Service
public class ProductService {
	
	  @Autowired
	  ProductRepository repository;
	  
	  private static final Logger log = LoggerFactory.getLogger(ProductService.class);
	 
      public String addProduct(Product product) throws Exception {
    	  try {
    		  repository.save(product);
    	  }catch(Exception ex) {
    		  log.info("Exception While Saving Product "+ex.getMessage()); 
    		  return ApplicationConstants.FAILURE_MESSAGE+product.getName();
    	  }
       
        return ApplicationConstants.SUCCESS_MESSAGE+product.getName();
      }
      
      public List < Product > getAllProducts() throws NoDataFoundException {
    	  log.info("getAllProducts Mehtod Entered");
    	  List < Product > productList = repository.findAll();
    	  if (productList.isEmpty()) {
              throw new NoDataFoundException();
          }
    	  log.info("getAllProducts Mehtod Closed");
          return productList;
      }
      
      public Optional< Product > getProduct(Integer id) throws ProductNotFoundException {
    	  log.info("getProduct Mehtod Entered");
    	  Optional< Product > product = repository.findById(id);
    			  
    	  if(!product.isPresent()) {
    		  throw new ProductNotFoundException(id);
    	  }
    	  log.info("getProduct Mehtod Closed");
          return product;
      }
}
