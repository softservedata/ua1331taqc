package com.softserve.stub;

import com.softserve.stub.dao.ProductDao;
import com.softserve.stub.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegrationTest {

	@Test
	public void checkLastDigits() {
		ProductDao productDao = new ProductDao();
		ProductService productService = new ProductService(productDao);
		String actual;
		String expected;
		//
		expected = "181";
		actual = productService.getLastDigits();
		//
		Assertions.assertEquals(actual, expected, "LastDigits ERROR");
	}
	
}
