package pl.entpoint.harmony.service.schedule;

import java.time.LocalDate;
import java.util.List;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;
import pl.entpoint.harmony.entity.schedule.enums.ScheduleStatus;

/**
 * @author Mateusz Dąbek
 * @created 20/11/2019
 */

public interface ScheduleRecordService {
	
	List<ScheduleRecord> getScheduleByStatus(ScheduleStatus status);
	
	List<ScheduleRecord> getScheduleBetweenDate(LocalDate startDate, LocalDate endDate);
	
	List<ScheduleRecord> getScheduleByEmployee(Employee employee);

	List<ScheduleRecord> getScheduleByDate(LocalDate date);

	List<ScheduleRecord> getScheduleByDateAndEmployee(LocalDate date, Employee employee);
	
	List<ScheduleRecord> getScheduleBetweenDateAndStatus(LocalDate startDate, LocalDate endDate, ScheduleStatus status);
	
	List<ScheduleRecord> getScheduleBetweenDateAndEmployee(LocalDate startDate, LocalDate endDate, Employee employee);

}
