package com.softserve.stub;

import com.softserve.stub.dao.IProductDao;

public class LastDotProductDaoStub implements IProductDao {

	public String getIPAddress() {
		return "aaa181.";
	}

	public String getIPAddress(String text) {
		return "aaa181.";
	}
}
