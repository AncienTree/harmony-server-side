package pl.entpoint.harmony.service.schedule;

import java.sql.Date;
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
	
	List<ScheduleRecord> getScheduleBetweenDate(Date startDate, Date endDate);
	
	List<ScheduleRecord> getScheduleByEmployee(Employee employee);

	List<ScheduleRecord> getScheduleByDate(Date date);

	List<ScheduleRecord> getScheduleByDateAndEmployee(Date date, Employee employee);
	
	List<ScheduleRecord> getScheduleBetweenDateAndStatus(Date startDate, Date endDate, ScheduleStatus status);
	
	List<ScheduleRecord> getScheduleBetweenDateAndEmployee(Date startDate, Date endDate, Employee employee);

}
