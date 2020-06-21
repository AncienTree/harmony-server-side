package pl.entpoint.harmony.util.exception.schedule;

import java.sql.Date;

/**
 * @author Mateusz Dąbek
 * @created 12/05/2020
 */
public class ScheduleNotFoundException extends RuntimeException{

    public ScheduleNotFoundException(Long id) {
        super("Nie znaleziono grafiku pod ID - " + id);
    }
}
