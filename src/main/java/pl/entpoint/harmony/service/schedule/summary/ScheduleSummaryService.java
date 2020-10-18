package pl.entpoint.harmony.service.schedule.summary;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.pojo.EmployeeScheduleList;
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
    List<ScheduleSummary> getEmployeeSchedules(String login);
    void create (LocalDate date, Employee employee);
    void massCreate(LocalDate date, List<Employee> employees);

    EmployeeScheduleList getListOfEmployees(LocalDate scheduleDate);
    ScheduleSummary getMySchedule(LocalDate localDate, String name);
    List<Employee> getEmployeeWithoutSchedule(LocalDate date);
}
