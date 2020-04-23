package pl.entpoint.harmony.service.schedule;

import java.sql.Date;
import java.util.List;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;

/**
 * @author Mateusz Dąbek
 * @created 20/11/2019
 */

public interface ScheduleRecordService {
	
	List<ScheduleRecord> getScheduleRecodByDateAndEmployee(Date date, Employee employee);
	
	void create (ScheduleRecord record);
}
