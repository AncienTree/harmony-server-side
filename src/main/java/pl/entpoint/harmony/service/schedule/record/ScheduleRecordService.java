package pl.entpoint.harmony.service.schedule.record;

import java.time.LocalDate;
import java.util.List;

import pl.entpoint.harmony.entity.dto.Presence;
import pl.entpoint.harmony.entity.dto.Record;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;
import pl.entpoint.harmony.entity.schedule.enums.ScheduleType;

/**
 * @author Mateusz Dąbek
 * @created 20/11/2019
 */

public interface ScheduleRecordService {
	
	List<ScheduleRecord> getScheduleRecordByDateAndEmployee(LocalDate date, Employee employee);
	ScheduleRecord getScheduleRecordByDateAndEmployeeAndTypes(LocalDate date, Employee employee, ScheduleType type);
	List<Presence> getScheduleRecordForPresence(LocalDate date);
	void create (Record record);
	void update (Record record);
}
