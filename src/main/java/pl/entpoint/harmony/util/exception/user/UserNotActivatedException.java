package pl.entpoint.harmony.util.exception.user;

public class UserNotActivatedException extends RuntimeException {
	public UserNotActivatedException() {
        super("Użytkownik jest nie aktywny.");
    }
}
