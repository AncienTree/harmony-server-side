package pl.entpoint.harmony.util.exception.employee;

/**
 * @author Mateusz Dąbek
 * @created 12/05/2020
 */
public class EmployeeNotWorkingException extends RuntimeException {
    public EmployeeNotWorkingException() {
        super("Użytkownik już nie pracuje.");
    }
}
