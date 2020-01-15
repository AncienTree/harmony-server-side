package pl.entpoint.harmony.service.schedule;

import java.time.LocalDate;
import java.util.List;

import pl.entpoint.harmony.models.employee.Employee;
import pl.entpoint.harmony.models.schedule.Schedule;
import pl.entpoint.harmony.models.schedule.enums.ScheduleStatus;

/**
 * @author Mateusz Dąbek
 * @created 20/11/2019
 */

public interface ScheduleService {
	
	List<Schedule> getScheduleByStatus(ScheduleStatus status);
	
	List<Schedule> getScheduleBetweenDate(LocalDate startDate, LocalDate endDate);
	
	List<Schedule> getScheduleByEmployee(Employee employee);

	List<Schedule> getScheduleByDate(LocalDate date);

	List<Schedule> getScheduleByDateAndEmployee(LocalDate date, Employee employee);
	
	List<Schedule> getScheduleBetweenDateAndStatus(LocalDate startDate, LocalDate endDate, ScheduleStatus status);
	
	List<Schedule> getScheduleBetweenDateAndEmployee(LocalDate startDate, LocalDate endDate, Employee employee);

}
