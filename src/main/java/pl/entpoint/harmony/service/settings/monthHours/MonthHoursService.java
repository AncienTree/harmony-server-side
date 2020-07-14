package pl.entpoint.harmony.service.settings.monthHours;

import pl.entpoint.harmony.entity.settings.MonthHours;

import java.util.Map;

/**
 * @author Mateusz Dąbek
 * @created 20.05.2020
 */


public interface MonthHoursService {
    MonthHours getMonthHoursByYear(String year);
    void create(MonthHours monthHours);
    void delete(MonthHours monthHours);
    void change(Map<String, String> monthHours);
}
