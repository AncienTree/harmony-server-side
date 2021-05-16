package pl.entpoint.harmony.util.exception.setting;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mateusz Dąbek
 * @created 19 maj 2020
 *
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User Line Not Found.")
public class UserLineNotFoundException extends RuntimeException {
    public UserLineNotFoundException(Long id) {
        super(String.format("Nie znaleziono linii o takim id %d", id));
    }
}
