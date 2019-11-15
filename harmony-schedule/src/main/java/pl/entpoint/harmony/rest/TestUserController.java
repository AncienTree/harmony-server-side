package pl.entpoint.harmony.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.entpoint.harmony.entity.User;
import pl.entpoint.harmony.service.TestUserService;

@RestController
@RequestMapping("/api")
public class TestUserController {

	@Autowired
	private TestUserService testUserService;

	@GetMapping("/users")
	public List<User> getListOfUsers() {
		
		return testUserService.getCustomers();
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable int id) {
		
		return testUserService.getCustomer(id);
	}

	@PostMapping("/users")
	public User addUser(@RequestBody User theUser) {
		testUserService.saveCustomer(theUser);
		
		return theUser;
	}
	
	@GetMapping("/usersP/{id}")
	public String getUserPesel(@PathVariable int id) {
		
		return testUserService.getUserPesel(id);
	}
	
}
