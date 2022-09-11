package com.ibm.productservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.ibm.productservice.entity.Product;
import com.ibm.productservice.exception.NoDataFoundException;

@SpringBootTest
public class ProductServiceTest {

	@Mock
	ProductService productService;

	@Before(value = "")
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllProductsTest() throws NoDataFoundException
	{
		List<Product> list = new ArrayList<Product>();
		Product prod1 = new Product(1, "TV", "TV Product", 1200.09f,"2022-09-07");
		Product prod2 = new Product(2, "AC", "AC Product", 1300.09f,"2022-09-07");

		list.add(prod1);
		list.add(prod2);

		when(productService.getAllProducts()).thenReturn(list);

		//test
		List<Product> empList = productService.getAllProducts();

		assertEquals(2, empList.size());
		verify(productService, times(1)).getAllProducts();
	}


}
