package com.softserve.edu.stub.service;

import com.softserve.edu.stub.dao.IProductDao;
import com.softserve.edu.stub.dao.ProductDao;

public class ProductService {

    private IProductDao productDao; // Dependency Inversion

    public ProductService(IProductDao productDao) {  // Dependency Injection
        System.out.println("Constructor ProductService(IProductDao productDao)");
        this.productDao = productDao;
    }

//	public ProductService() {
//		System.out.println("Constructor ProductService()");
//		productDao = new ProductDao();
//	}

    public IProductDao getProductDao() {
        return productDao;
    }

    public String getLastDigits() {
        String origin = getProductDao().getIPAddress();
        return origin.substring(origin.lastIndexOf(".") + 1);
    }

    public String getLastDigits(String text) {
        String origin = getProductDao().getIPAddress(text);
        //System.out.println("\torigin = " + origin);
        //String res  = origin.substring(origin.lastIndexOf(".") + 1);
        //System.out.println("\tres = " + res);
        return origin.substring(origin.lastIndexOf(".") + 1);
    }

}
