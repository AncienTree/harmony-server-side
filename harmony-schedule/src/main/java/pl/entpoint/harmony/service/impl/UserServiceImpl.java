package pl.entpoint.harmony.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.User;
import pl.entpoint.harmony.entity.enums.Roles;
import pl.entpoint.harmony.repository.UserRepository;
import pl.entpoint.harmony.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmployeeServiceImpl emplService;
	
	@Override
	public List<User> getUsers() {
		
		return userRepository.findAll();
	}
	
	@Override
	public List<User> getUsersByRoles(Roles role) {
		
		return userRepository.findByRole(role);
	}

	@Override
	public void createUser(User user) {
		userRepository.save(user);		
	}

	@Override
	public User getUser(int theId) {
		Optional<User> result = userRepository.findById(theId);
		
		User theUser = null;
		if(result.isPresent()) {
			theUser = result.get();
		} else {
			throw new RuntimeException("Nie znaleziono użytkownika pod ID - " + theId);
		}
		return theUser;
	}

	@Override
	public void deleteUser(int theId) {
		userRepository.deleteById(theId);
		
	}

	@Override
	public void changeStatus(int id, boolean status) {
		Optional<User> result = userRepository.findById(id);
		
		User theUser = null;
		if(result.isPresent()) {
			theUser = result.get();
		} else {
			throw new RuntimeException("Nie znaleziono użytkownika pod ID - " + id);
		}
		theUser.setStatus(status);
		userRepository.save(theUser);
	}		
		
}
	
	
