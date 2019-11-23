package pl.entpoint.harmony.service;

import java.time.LocalDate;
import java.util.List;

import pl.entpoint.harmony.entity.Employee;
import pl.entpoint.harmony.entity.Schedule;
import pl.entpoint.harmony.entity.enums.ScheduleStatus;

/**
 * @author Mateusz Dąbek
 * Created on Nov 20, 2019
 * m.dabek@entpoint.pl
 */
public interface ScheduleService {
	
	public List<Schedule> getScheduleByStatus(ScheduleStatus status);
	
	public List<Schedule> getScheduleBetweenDate(LocalDate startDate, LocalDate endDate);
	
	public List<Schedule> getScheduleByEmployee(Employee empl);

	public List<Schedule> getScheduleByDate(LocalDate date);

	public List<Schedule> getScheduleByDateAndEmployee(LocalDate date, Employee empl);
	
	public List<Schedule> getScheduleBetweenDateAndStatus(LocalDate startDate, LocalDate endDate, ScheduleStatus status);
	
	public List<Schedule> getScheduleBetweenDateAndEmployee(LocalDate startDate, LocalDate endDate, Employee empl);

}
