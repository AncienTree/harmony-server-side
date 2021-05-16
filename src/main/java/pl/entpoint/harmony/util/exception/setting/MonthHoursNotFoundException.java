package pl.entpoint.harmony.util.exception.setting;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mateusz Dąbek
 * @created 19 maj 2020
 *
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Month Hours Not Found.")
public class MonthHoursNotFoundException extends RuntimeException {
    public MonthHoursNotFoundException() {
        super("Nie znaleziono takiego zestawienia roboczogodzin");
    }
}
