package pl.entpoint.harmony.rest;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.entpoint.harmony.entity.Employee;
import pl.entpoint.harmony.entity.User;
import pl.entpoint.harmony.service.TestUserService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TestUserController {

	private static final Logger logger = LoggerFactory.getLogger(TestUserController.class);
	
	@Autowired
	private TestUserService testUserService;


	@GetMapping("/users")
	public List<User> getListOfUsers() {
		List<User> users = testUserService.getCustomers();
		logger.info("=====> Pobieranie danych ");
		
		return users;
	}
		
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable int id) {
		
		return testUserService.getCustomer(id);
	}

	@PostMapping("/users")
	public User addUser(@RequestBody User theUser) {
		theUser.setId(0);
		theUser.newUser(theUser);
		theUser.getEmployeeId().newEmployee();
		testUserService.saveCustomer(theUser);
		
		return theUser;
	}
	
	@PostMapping("/usersA")
	public User createNewUser(@RequestBody Map<String, String> body) {
		User theUser = new User(
				body.get("login"),
				body.get("password"));
		theUser.setId(0);
		Employee theEmp = new Employee(
				body.get("name"),
				body.get("lastName"), 
				Long.parseLong(body.get("pesel")));
		theUser.setEmployeeId(theEmp);
		testUserService.saveCustomer(theUser);
		
		return theUser;
	}
	
	@GetMapping("/usersP/{id}")
	public String getUserPesel(@PathVariable int id) {
		
		return testUserService.getUserPesel(id);
	}
	
}
