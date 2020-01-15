package pl.entpoint.harmony.rest;

import java.util.List;
import java.util.Map;

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
public class UserController {

	@Autowired
	private UserService userService;


	@GetMapping("/users")
	public List<User> getListOfUsers() {
		List<User> users = userService.getUsers();
		log.info("Pobieranie całej listy użytkowników");
		
		return users;
	}
		
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable int id) {
		//TODO zabezpieczenie przed nie znlezieniem Id
		log.info("Pobrano użytkownika o Id: " + id);
		return userService.getUser(id);
	}
	
	@PatchMapping("/users/{id}")
	public void updateStatus(boolean status, @PathVariable int id) {
		userService.changeStatus(id, status);
		log.info("Zmiana statusu dla id: " + id + " na status: " + status);
	}
	
	@PatchMapping("/users/opt/{id}")
	public void update(@RequestBody Map<String,Object> user, @PathVariable int id) {
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
