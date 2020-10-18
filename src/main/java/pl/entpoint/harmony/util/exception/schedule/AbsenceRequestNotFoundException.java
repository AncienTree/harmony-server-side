package pl.entpoint.harmony.util.exception.schedule;

/**
 * @author Mateusz Dąbek
 * @created 12/05/2020
 */
public class AbsenceRequestNotFoundException extends RuntimeException{

    public AbsenceRequestNotFoundException(Long id) {
        super("Wniosek urlopowy o takim ID nie istnieje w bazie danych - " + id);
    }
}
