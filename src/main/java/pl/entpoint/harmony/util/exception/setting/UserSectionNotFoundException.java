package pl.entpoint.harmony.util.exception.setting;

public class UserSectionNotFoundException extends RuntimeException {
    public UserSectionNotFoundException(Long id) {
        super("Nie znaleziono sekcji o takim id " + id);
    }
}
