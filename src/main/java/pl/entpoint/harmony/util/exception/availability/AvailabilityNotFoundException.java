package pl.entpoint.harmony.util.exception.availability;

import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 12/05/2020
 */

public class AvailabilityNotFoundException extends RuntimeException{

    public AvailabilityNotFoundException(LocalDate date) {
        super("Nie znaleziono dyspozycyjności o dacie " + date + " w bazie danych.");
    }
}
