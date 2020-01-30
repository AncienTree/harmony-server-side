package pl.entpoint.harmony.service.schedule;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleSummary;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 30/01/2020
 */
public interface ScheduleSummaryService {
    ScheduleSummary getScheduleByDateAndEmployee(LocalDate date, Employee employee);

    List<ScheduleSummary> getScheduleByDate(LocalDate date);
}
