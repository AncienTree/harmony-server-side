package pl.entpoint.harmony.service.schedule.absence;

import java.security.Principal;
import java.util.List;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.AbsenceRecord;

/**
 * @author Mateusz Dąbek
 * @created 22 wrz 2020
 * 
 */

public interface AbsenceRecordService {
	
	List<AbsenceRecord> getAll();
	List<AbsenceRecord> getEmployeeRequests(Employee employee);
	List<AbsenceRecord> getMyRequests(Principal principal);
	void submiteRequest(AbsenceRecord record);
	void acceptRequest(AbsenceRecord record);
	void declineRequest(AbsenceRecord record); 

}
