package pl.entpoint.harmony.util.exception.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mateusz Dąbek
 * @created 12/05/2020
 */

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Employee Not Working.")
public class EmployeeNotWorkingException extends RuntimeException {
    public EmployeeNotWorkingException() {
        super("Użytkownik już nie pracuje.");
    }
}
