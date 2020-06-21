package pl.entpoint.harmony.util.exception;

public class UserNotActivatedException extends RuntimeException {

	private static final long serialVersionUID = 3226752854350634837L;

	public UserNotActivatedException() {
        super("Użytkownik jest nie aktywny.");
    }
}
