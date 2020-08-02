package pl.entpoint.harmony.service.schedule.record;

import java.time.LocalDate;
import java.util.List;

import pl.entpoint.harmony.entity.dto.Presence;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;

/**
 * @author Mateusz Dąbek
 * @created 20/11/2019
 */

public interface ScheduleRecordService {
	
	List<ScheduleRecord> getScheduleRecordByDateAndEmployee(LocalDate date, Employee employee);
	List<Presence> getScheduleRecordForPresence(LocalDate date);
	void create (ScheduleRecord record);
}
