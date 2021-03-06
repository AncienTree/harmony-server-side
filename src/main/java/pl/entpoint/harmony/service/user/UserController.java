package pl.entpoint.harmony.service.user;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pl.entpoint.harmony.entity.pojo.controller.UserPojo;
import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.util.BCrypt;

/**
 * @author Mateusz Dąbek
 * @created 19/11/2019
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@AllArgsConstructor
@Api(tags = "User Controller")
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "Get list of users", nickname = "Get list of users")
    @GetMapping("/users")
    public List<User> getListOfUsers() {
        List<User> users = userService.getUsers();
        log.info("Pobieranie całej listy użytkowników");

        return users;
    }

    @GetMapping("/users/{id}")
    @ApiOperation(value = "Get user by id")
    @ApiImplicitParam(name = "id", value = "User id", required = true, dataType = "long", paramType = "path", defaultValue = "1")
    public User getUser(@PathVariable Long id) {
        log.info("Pobrano użytkownika o Id: " + id);
        return userService.getUser(id);
    }

    @PatchMapping("/users/{id}/status")
    @ApiOperation(value = "Update user status by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User id", required = true, dataType = "long", paramType = "path", defaultValue = "1"),
            @ApiImplicitParam(name = "status", value = "Status flag", required = true, dataType = "boolean", paramType = "body", defaultValue = "false")
    })
    public ResponseEntity<String> updateStatus(@RequestBody boolean status, @PathVariable Long id) {
        userService.changeStatus(id, status);
        log.info("Zmiana statusu dla id: " + id + " na status: " + status);
        return new ResponseEntity<>("Zmiana statusu dla id: \" + id + \" na status: \" + status", HttpStatus.OK);
    }

    @PatchMapping("/users/change")
    @ApiOperation(value = "Change user settings")
    @ApiImplicitParam(name = "user", value = "User", required = true, dataType = "UserPojo", paramType = "body")
    public ResponseEntity<String> update(@RequestBody UserPojo user) {
        User theUser = getUser(user.getId());
        theUser.setStatus(user.isStatus());
        if (user.getPassword() != null) {
            theUser.setPassword(BCrypt.encrypt(user.getPassword()));
        }
        theUser.setRole(user.getRole());

        userService.createUser(theUser);
        log.info("Zmiana statusu loginu: " + theUser.getLogin() + " na status: " + theUser.isStatus());

        return new ResponseEntity<>("Zmiana statusu loginu: " + theUser.getLogin() + " na status: " + theUser.isStatus(), HttpStatus.OK);
    }
}
