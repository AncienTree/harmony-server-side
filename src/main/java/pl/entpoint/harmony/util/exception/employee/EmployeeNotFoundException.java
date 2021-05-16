package pl.entpoint.harmony.util.exception.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mateusz Dąbek
 * @created 12/05/2020
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Employee Not Found.")
public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long id) {
        super(String.format("Nie znaleziono użytkownika pod takim id: %d", id));
    }

    public EmployeeNotFoundException() {
        super("Nie znaleziono użytkownika pod takim numerem PESEL");
    }
    
    public EmployeeNotFoundException(String login) {
        super(String.format("Nie znaleziono użytkownika pod takim loginem: %s", login));
    }
}
