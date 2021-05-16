package pl.entpoint.harmony.util.exception.schedule;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mateusz Dąbek
 * @created 12/05/2020
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Record Not Found.")
public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(Long id) {
        super(String.format("Nieznalezione rekordu o podanym ID - %d", id));
    }
}
