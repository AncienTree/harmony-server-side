package pl.entpoint.harmony.service.settings.monthHours;

import pl.entpoint.harmony.entity.settings.MonthHours;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author Mateusz Dąbek
 * @created 20.05.2020
 */


public interface MonthHoursService {
    List<MonthHours> getAll();
    void create(MonthHours monthHours);
    void delete(MonthHours monthHours);
    void change(Map<String, String> monthHours);
    int checkMonthHours(LocalDate date);
}
