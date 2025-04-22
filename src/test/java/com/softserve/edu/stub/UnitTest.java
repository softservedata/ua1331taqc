package com.softserve.edu.stub;


import com.softserve.edu.stub.dao.IProductDao;
import com.softserve.edu.stub.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnitTest {

    @Test
    public void checkLastDigits() {
        IProductDao productDao = new ValidProductDaoStub();
        ProductService productService = new ProductService(productDao);
        String actual;
        String expected;
        //
        expected = "123";
        actual = productService.getLastDigits();
        //
        Assertions.assertEquals(actual, expected, "LastDigits ERROR");
    }

    @Test
    public void checkOutDot() {
        IProductDao productDao = new OutDotProductDaoStub();
        ProductService productService = new ProductService(productDao);
        String actual;
        String expected;
        //
        expected = "aaa181";
        actual = productService.getLastDigits();
        //
        Assertions.assertEquals(actual, expected, "LastDigits ERROR");
    }

    @Test
    public void checkLastDot() {
        IProductDao productDao = new LastDotProductDaoStub();
        ProductService productService = new ProductService(productDao);
        String actual;
        String expected;
        //
        expected = "";
        actual = productService.getLastDigits();
        //
        Assertions.assertEquals(actual, expected, "LastDigits ERROR");
    }

}
