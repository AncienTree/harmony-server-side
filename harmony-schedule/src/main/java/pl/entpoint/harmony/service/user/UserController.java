package pl.entpoint.harmony.service.user;

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
import pl.entpoint.harmony.models.user.User;
import pl.entpoint.harmony.models.user.enums.Roles;
import pl.entpoint.harmony.util.BCrypt;

/**
 * @author Mateusz Dąbek
 * @created 19/11/2019
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class UserController {

    private UserService userService;

    @Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
    public List<User> getListOfUsers() {
        List<User> users = userService.getUsers();
        log.info("Pobieranie całej listy użytkowników");

        return users;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        //TODO zabezpieczenie przed nie znlezieniem Id
        log.info("Pobrano użytkownika o Id: " + id);
        return userService.getUser(id);
    }

    @PatchMapping("/users/{id}")
    public void updateStatus(@RequestBody boolean status, @PathVariable Long id) {
        userService.changeStatus(id, status);
        log.info("Zmiana statusu dla id: " + id + " na status: " + status);
    }

    @PatchMapping("/users/opt/{id}")
    public void update(@RequestBody Map<String, Object> user, @PathVariable Long id) {
        User theUser = getUser(id);
        theUser.setStatus((boolean) user.get("status"));
        if (!(user.get("password") == null)) {
            theUser.setPassword(BCrypt.encrypt((String) user.get("password")));
        }
        theUser.setRole(Roles.valueOf((String) user.get("role")));

        userService.createUser(theUser);
        log.info("Zmiana statusu loginu: " + theUser.getLogin() + " na status: " + theUser.isStatus());
    }
}
