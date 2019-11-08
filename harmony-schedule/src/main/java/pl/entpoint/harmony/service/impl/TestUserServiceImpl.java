package pl.entpoint.harmony.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.dao.TestUserDao;
import pl.entpoint.harmony.entity.User;
import pl.entpoint.harmony.service.TestUserService;

@Service
public class TestUserServiceImpl implements TestUserService{

	@Autowired
	TestUserDao testUserDao;
	
	@Override
	public List<User> getCustomers() {
		
		return testUserDao.getCustomers();
	}

	@Override
	public void saveCustomer(User theCustomer) {
		testUserDao.saveCustomer(theCustomer);		
	}

	@Override
	public User getCustomer(int theId) {
		return testUserDao.getCustomer(theId);
	}

	@Override
	public void deleteCustomer(int theId) {
		testUserDao.deleteCustomer(theId);
		
	}
	
}
