package pl.entpoint.harmony.service.settings.monthHours;

import pl.entpoint.harmony.entity.pojo.controller.MonthlyHoursPojo;
import pl.entpoint.harmony.entity.settings.MonthHours;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 20.05.2020
 */


public interface MonthHoursService {
    List<MonthHours> getAll();
    int checkMonthHours(LocalDate date);
    void create(MonthHours monthHours);
    void change(MonthlyHoursPojo monthHours);
    void delete(int id);
}
