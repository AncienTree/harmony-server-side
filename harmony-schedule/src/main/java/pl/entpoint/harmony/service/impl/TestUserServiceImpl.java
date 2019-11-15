package pl.entpoint.harmony.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.Employee;
import pl.entpoint.harmony.entity.User;
import pl.entpoint.harmony.repository.TestUserRepository;
import pl.entpoint.harmony.service.TestUserService;

@Service
public class TestUserServiceImpl implements TestUserService{

	@Autowired
	TestUserRepository testUserRepository;
	
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
		} else {
			throw new RuntimeException("Nie znaleziono użytkownika pod ID - " + theId);
		}
		return theUser;
	}

	@Override
	public void deleteCustomer(int theId) {
		testUserRepository.deleteById(theId);
		
	}

	@Override
	public String getUserPesel(int id) {
		User temp2 = getCustomer(id);
		
		Employee temp = temp2.getEmployeeId();
		
		return temp.getFirstName() + " " + temp.getLastName() + " Pesel: " + temp.getPesel();
	}
	
	
}
