package pl.entpoint.harmony.entity.schedule.enums;

/**
 * @author Mateusz Dąbek
 * @created 16/11/2019
 */
// TODO Utworzyć nowy status dla planera!
public enum AbsenceStatus {
    NOWY("NEW"),
    ODRZUCONY("REJECTED"),
    ZAAKCEPTOWANY("ACCPETED");

    private String value;

    AbsenceStatus(String value) {
        this.value = value;
    }
}