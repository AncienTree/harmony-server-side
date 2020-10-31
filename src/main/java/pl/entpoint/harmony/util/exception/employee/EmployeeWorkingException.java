package pl.entpoint.harmony.util.exception.employee;

/**
 * @author Mateusz Dąbek
 * @created 31/10/2020
 */
public class EmployeeWorkingException extends RuntimeException {
    public EmployeeWorkingException() {
        super("Użytkownik jest już aktywny i nie można go przywrócić.");
    }
}
