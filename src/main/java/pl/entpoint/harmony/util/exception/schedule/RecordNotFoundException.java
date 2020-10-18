package pl.entpoint.harmony.util.exception.schedule;

/**
 * @author Mateusz Dąbek
 * @created 12/05/2020
 */
public class RecordNotFoundException extends RuntimeException{

    public RecordNotFoundException(Long id) {
        super("Nieznaleziono rekordu o podanym ID - " + id);
    }
}
