package pl.entpoint.harmony.util.exception.schedule;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 12/05/2020
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Schedule already exist.")
public class ScheduleExistException extends RuntimeException{
    public ScheduleExistException(LocalDate date) {
        super(String.format("Grafik o dacie %s już istnieje w bazie danych.", date));
    }
    public ScheduleExistException() {
        super("Grafik już istnieje w bazie danych.");
    }
}
