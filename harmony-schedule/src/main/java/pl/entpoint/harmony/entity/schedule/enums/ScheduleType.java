package pl.entpoint.harmony.entity.schedule.enums;

/**
 * @author Mateusz Dąbek
 * @created 16/11/2019
 */

public enum ScheduleType {
    DYSPOZYCYJNOSC("DYSPO"),
    DOSTEPNOSC("DOSTP"),
    GRAFIK("GRAFIK"),
    ZALOGOWANIE("LOGIN"),
    JITSI("JITSI"),
    OBECNOSC("OBEC"),
    DZWONIENIE("DZWON");

    private final String value;

    ScheduleType(String value) {
        this.value = value;
    }

    public boolean checkValue(String text){
        return !this.value.equals(text);
    }
}
