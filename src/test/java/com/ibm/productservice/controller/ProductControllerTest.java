package com.ibm.productservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.Controller;

import com.google.gson.Gson;
import com.ibm.productservice.dao.ProductRepository;
import com.ibm.productservice.entity.Product;
import com.ibm.productservice.service.ProductService;



@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
	@Mock
	ProductService productService;
	
	@MockBean
	ProductRepository productRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	

	

	

	
	




	@Test
	public void createProduct() throws Exception {
		Product prod1 = new Product(1, "Jewellery", "Jewellery Product", 1300.09f,"2022-09-07");

		// productService.save to respond back with mockCourse
		Mockito.when(productRepository.save(
						Mockito.any(Product.class))).thenReturn(prod1);
		Gson gson = new Gson();
		String productJson = gson.toJson(prod1);

		// Send product as body to /saveProduct
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/saveProduct")
				.accept(MediaType.APPLICATION_JSON).content(productJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(200,response.getStatus());

	}

}
