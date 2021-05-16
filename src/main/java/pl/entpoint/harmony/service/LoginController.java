package pl.entpoint.harmony.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import pl.entpoint.harmony.entity.pojo.LoginCredentials;

import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 27.10.2020
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags = "#Login")
public class LoginController {

    @PostMapping("/login")
    @ApiOperation(value = "Log in to the system.", nickname = "Log in to the system.")
    @ApiImplicitParam(name = "credentials", value = "Login credentials", required = true, dataType = "LoginCredentials", paramType = "body")
    public void login(@RequestBody LoginCredentials credentials) {
    }
}
