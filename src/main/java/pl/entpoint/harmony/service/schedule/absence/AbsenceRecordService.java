package pl.entpoint.harmony.service.schedule.absence;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.pojo.controller.AbsencePojo;
import pl.entpoint.harmony.entity.schedule.AbsenceRecord;

/**
 * @author Mateusz Dąbek
 * @created 22 wrz 2020
 * 
 */

public interface AbsenceRecordService {
	
	List<AbsenceRecord> getAll();
	List<AbsenceRecord> getEmployeeRequests(Long id);
	List<AbsenceRecord> getMyRequests(Principal principal);
	void submiteRequest(List<AbsencePojo> recordd, String login);
	void acceptRequest(Long id, Principal principal);
	void declineRequest(Long id); 
	boolean isAlreadyInDb(Employee employee, LocalDate date);

}
