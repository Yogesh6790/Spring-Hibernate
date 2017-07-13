package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.ITestDao;

@Service
public class TestService implements ITestService {

	@Autowired
	private ITestDao testDao;
	
	@Override
	public List getDetails(String country) throws Exception {
		return testDao.getDetails(country);
	}
	
	public ITestDao getTestDao() {
		return testDao;
	}

	public void setTestDao(ITestDao testDao) {
		this.testDao = testDao;
	}

}
