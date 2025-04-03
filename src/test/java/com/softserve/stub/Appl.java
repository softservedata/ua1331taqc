package com.softserve.stub;

import com.softserve.stub.dao.IProductDao;
import com.softserve.stub.dao.ProductDao;
import com.softserve.stub.service.ProductService;

public class Appl {

	public static void main(String[] args) {
		//IProductDao userDao = new OutDotProductDaoStub();
		IProductDao userDao = new ProductDao();
		ProductService productService = new ProductService(userDao);
		System.out.println("result = " + productService.getLastDigits());
		System.out.println("result = " + productService.getLastDigits(" form data"));
	}
}
