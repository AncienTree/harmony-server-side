package pl.entpoint.harmony.rest;

import java.util.List;
import java.util.Map;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pl.entpoint.harmony.entity.User;
import pl.entpoint.harmony.entity.enums.Roles;
import pl.entpoint.harmony.service.UserService;
import pl.entpoint.harmony.util.BCrypt;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@Api(
	name="User API",
	description = "Do tworzenia i pobrania listy użytkowników do aplikacji",
	stage = ApiStage.ALPHA
)
public class UserController {

	@Autowired
	private UserService userService;


	@GetMapping("/users")
	@ApiMethod(description = "Pobiera całą listę użytkowników")
	public List<User> getListOfUsers() {
		List<User> users = userService.getUsers();
		log.info("Pobieranie całej listy użytkowników");
		
		return users;
	}
		
	@GetMapping("/users/{id}")
	@ApiMethod(description = "Pobiera określonego użytkownika")
	public User getUser(@ApiPathParam(name = "Id") @PathVariable int id) {
		//TODO zabezpieczenie przed nie znlezieniem Id
		log.info("Pobrano użytkownika o Id: " + id);
		return userService.getUser(id);
	}
	
//	@PostMapping("/user")
//	@ApiMethod(description = "Utworzenie nowego loginu")
//	public User createNewUser(@RequestBody Map<String, String> body) {
//		//TODO zabezpieczenie przed utworzeniem tego samego loginu
//		// stworzono blokade na bazie
//		User theUser = new User(
//				body.get("login"),
//				BCrypt.decrypt(body.get("password")));
//		theUser.setId(0);
//		Employee theEmp = new Employee(
//				body.get("name"),
//				body.get("lastName"), 
//				Long.parseLong(body.get("pesel")));
//		theUser.setEmployee(theEmp);
//		userService.createUser(theUser);
//		
//		return theUser;
//	}
	
	@PatchMapping("/users/{id}")
	@ApiMethod(description = "Zmiana statusu loginu")
	public void updateStatus(@ApiPathParam(name = "Status")@RequestBody boolean status,
							@ApiPathParam(name = "Id") @PathVariable int id) {
		//boolean active = status.get("status");
		
		userService.changeStatus(id, status);
		log.info("Zmiana statusu dla id: " + id + " na status: " + status);
	}
	
	@PatchMapping("/users/opt/{id}")
	@ApiMethod(description = "Zmiana ustawień użytkownika")
	public void update(@ApiPathParam(name = "User")@RequestBody Map<String,Object> user,
							@ApiPathParam(name = "Id") @PathVariable int id) {
		User theUser = getUser(id);
		theUser.setStatus((boolean)user.get("status"));
		if(!(user.get("password") == null)) {
			theUser.setPassword(BCrypt.decrypt((String)user.get("password")));
		}
		theUser.setRole(Roles.valueOf((String) user.get("role")));
		
		userService.createUser(theUser);
		log.info("Zmiana statusu loginu: " + theUser.getLogin() + " na status: " + theUser.isStatus());
	}
	
	
	
	
	
}
