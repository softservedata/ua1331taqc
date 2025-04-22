package com.softserve.edu.stub.dao;


public class ProductDao {
    public String getIPAddress() {
        System.out.println("***Running ProductDao getIPAddress()");
        return "192.168.103.181";
    }

    public String getIPAddress(String text) {
        System.out.println("***Running ProductDao getIPAddress(String text)");
        return "192.168.103.181" + text;
    }
}
