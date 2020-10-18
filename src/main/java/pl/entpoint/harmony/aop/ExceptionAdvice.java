package pl.entpoint.harmony.aop;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.entpoint.harmony.util.exception.user.UserNotActivatedException;

/**
 * @author Mateusz Dąbek
 * @created 03/02/2020
 */

@RestControllerAdvice
public class ExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotActivatedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String userNotActivatedHandler(UserNotActivatedException e) {
        return e.getMessage();
    }
}
