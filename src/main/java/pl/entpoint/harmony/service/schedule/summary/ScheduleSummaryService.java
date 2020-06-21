package pl.entpoint.harmony.service.schedule.summary;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleSummary;

import java.sql.Date;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 30/01/2020
 */
public interface ScheduleSummaryService {
    ScheduleSummary getScheduleByDateAndEmployee(Date date, Employee employee);
    List<ScheduleSummary> getScheduleByDate(Date date);
    ScheduleSummary create (Date date, Employee employee);
    void massCreate(Date date, List<Employee> employees);
}
