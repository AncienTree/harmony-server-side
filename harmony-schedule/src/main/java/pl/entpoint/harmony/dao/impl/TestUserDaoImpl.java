package pl.entpoint.harmony.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.entpoint.harmony.dao.TestUserDao;
import pl.entpoint.harmony.entity.User;
import pl.entpoint.harmony.repository.TestUserRepository;

@Component
public class TestUserDaoImpl implements TestUserDao {

	@Autowired
	private TestUserRepository testUserRepository;
	
	@Override
	public List<User> getCustomers() {
		return testUserRepository.findAll();
	}

	@Override
	public void saveCustomer(User theCustomer) {
		testUserRepository.save(theCustomer);
		
	}

	@Override
	public User getCustomer(int theId) {
		Optional<User> result = testUserRepository.findById(theId);
		
		User theUser = null;
		
		if(result.isPresent()) {
			theUser = result.get();
		}else {
			// we didn't find the employee
			throw new RuntimeException("Did not find user id - " + theId);
		}
		return theUser;
	}

	@Override
	public void deleteCustomer(int theId) {
		testUserRepository.deleteById(theId);
		
	}	

}
