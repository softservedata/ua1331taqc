package com.softserve.stub;

import com.softserve.stub.dao.IProductDao;

public class ValidProductDaoStub implements IProductDao {

	public String getIPAddress() {
		return "aaa.123";
	}

	public String getIPAddress(String text) {
		return "aaa.123";
	}
}
