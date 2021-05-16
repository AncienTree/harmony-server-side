package pl.entpoint.harmony.util.exception.schedule;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mateusz Dąbek
 * @created 12/05/2020
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Schedule Not Found.")
public class ScheduleNotFoundException extends RuntimeException{
    public ScheduleNotFoundException(Long id) {
        super(String.format("Nie znaleziono grafiku pod ID - %d", id));
    }

    public ScheduleNotFoundException() {
        super("Grafik nie został znaleziony.");
    }
}
