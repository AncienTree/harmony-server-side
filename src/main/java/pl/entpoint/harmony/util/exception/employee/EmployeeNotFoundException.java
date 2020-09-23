package pl.entpoint.harmony.util.exception.employee;

/**
 * @author Mateusz Dąbek
 * @created 12/05/2020
 */
public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long id) {
        super("Nie znaleziono użytkownika pod takim id: " + id);
    }

    public EmployeeNotFoundException() {
        super("Nie znaleziono użytkownika pod takim numerem PESEL");
    }
    
    public EmployeeNotFoundException(String login) {
        super("Nie znaleziono użytkownika pod takim loginem: " + login);
    }
}
