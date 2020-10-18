package pl.entpoint.harmony.util.exception.setting;

public class UserLineNotFoundException extends RuntimeException {
    public UserLineNotFoundException(Long id) {
        super("Nie znaleziono linii o takim id " + id);
    }
}
