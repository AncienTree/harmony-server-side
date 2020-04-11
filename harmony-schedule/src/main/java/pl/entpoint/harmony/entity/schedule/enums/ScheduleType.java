package pl.entpoint.harmony.entity.schedule.enums;

import pl.entpoint.harmony.entity.schedule.Schedule;

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

    private String value;

    ScheduleType(String value) {
        this.value = value;
    }

    public boolean checkValue(String text){
        return this.value.equals(text);
    }
}
