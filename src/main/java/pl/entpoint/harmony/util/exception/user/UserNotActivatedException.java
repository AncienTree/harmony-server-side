package pl.entpoint.harmony.util.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mateusz Dąbek
 * @created 19 maj 2020
 *
 */

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "User Not Activated.")
public class UserNotActivatedException extends RuntimeException {
	public UserNotActivatedException() {
        super("Użytkownik jest nie aktywny.");
    }
}
