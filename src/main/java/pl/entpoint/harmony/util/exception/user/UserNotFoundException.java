package pl.entpoint.harmony.util.exception.user;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException() {
        super("Użytkownik nie został znaleziony.");
    }
	public UserNotFoundException(String login) {
        super("Użytkownik o loginie " + login + " nie został znaleziony.");
    }
}
