package pl.entpoint.harmony.util.exception.schedule;

import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 12/05/2020
 */
public class ScheduleExisteException extends RuntimeException{

    public ScheduleExisteException(LocalDate date) {
        super("Grafik o dacie " + date + " już istnieje w bazie.");
    }
}
