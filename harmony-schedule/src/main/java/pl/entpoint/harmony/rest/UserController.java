package pl.entpoint.harmony.rest;

import java.util.List;
import java.util.Map;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
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
import pl.entpoint.harmony.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Api(
	name="User API",
	description = "Do tworzenia i pobrania listy użytkowników do aplikacji",
	stage = ApiStage.ALPHA
)
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService testUserService;


	@GetMapping("/users")
	@ApiMethod(description = "Pobiera całą listę użytkowników")
	public List<User> getListOfUsers() {
		List<User> users = testUserService.getUsers();
		logger.info("=====> Pobieranie danych ");
		
		return users;
	}
		
	@GetMapping("/users/{id}")
	@ApiMethod(description = "Pobiera określonego użytkownika")
	public User getUser(@ApiPathParam(name = "Id") @PathVariable int id) {
		
		return testUserService.getUser(id);
	}

	@PostMapping("/users")
	public User addUser(@RequestBody User theUser) {
		theUser.setId(0);
		theUser.newUser(theUser);
		theUser.getEmployee().newEmployee();
		testUserService.createUser(theUser);
		
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
		theUser.setEmployee(theEmp);
		testUserService.createUser(theUser);
		
		return theUser;
	}
	
}
