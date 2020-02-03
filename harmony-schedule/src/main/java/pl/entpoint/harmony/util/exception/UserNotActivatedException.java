package pl.entpoint.harmony.util.exception;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserNotActivatedException extends RuntimeException {

    public UserNotActivatedException() {
        super("Użytkownik jest nie aktywny.");
    }
}
