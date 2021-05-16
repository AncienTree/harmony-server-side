package pl.entpoint.harmony.util.exception.setting;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mateusz Dąbek
 * @created 19 maj 2020
 *
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User Section Not Found.")
public class UserSectionNotFoundException extends RuntimeException {
    public UserSectionNotFoundException(Long id) {
        super(String.format("Nie znaleziono sekcji o takim id %d", id));
    }
}
