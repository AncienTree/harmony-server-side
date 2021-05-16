package pl.entpoint.harmony.util.exception.schedule;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mateusz Dąbek
 * @created 12/05/2020
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Absence Request Not Found.")
public class AbsenceRequestNotFoundException extends RuntimeException{
    public AbsenceRequestNotFoundException(Long id) {
        super(String.format("Wniosek urlopowy o takim ID nie istnieje w bazie danych - %d", id));
    }
}
