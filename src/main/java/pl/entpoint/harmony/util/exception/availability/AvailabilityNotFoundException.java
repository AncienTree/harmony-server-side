package pl.entpoint.harmony.util.exception.availability;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 12/05/2020
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Availability Not Found.")
public class AvailabilityNotFoundException extends RuntimeException{

    public AvailabilityNotFoundException(LocalDate date) {
        super(String.format("Nie znaleziono dyspozycyjności o dacie %s w bazie danych.", date));
    }
}
