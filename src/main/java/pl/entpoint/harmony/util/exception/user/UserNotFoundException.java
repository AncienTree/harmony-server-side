package pl.entpoint.harmony.util.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mateusz Dąbek
 * @created 19 maj 2020
 *
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User Not Found.")
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException() {
        super("Użytkownik nie został znaleziony.");
    }
	public UserNotFoundException(String login) {
        super(String.format("Użytkownik o loginie %s nie został znaleziony.", login));
    }
}
