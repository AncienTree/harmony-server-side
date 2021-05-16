package pl.entpoint.harmony.util.exception.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mateusz Dąbek
 * @created 31/10/2020
 */

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Employee Working and can't be restored.")
public class EmployeeWorkingException extends RuntimeException {
    public EmployeeWorkingException() {
        super("Użytkownik jest już aktywny i nie można go przywrócić.");
    }
}
