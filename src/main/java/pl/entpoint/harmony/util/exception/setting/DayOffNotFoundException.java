package pl.entpoint.harmony.util.exception.setting;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mateusz Dąbek
 * @created 19 maj 2020
 *
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Day Off Not Found.")
public class DayOffNotFoundException extends RuntimeException {
    public DayOffNotFoundException() {
        super("Nie znaleziono wolnego dnia od pracy");
    }
}
