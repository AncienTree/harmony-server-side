package pl.entpoint.harmony.service.schedule.absence;

import java.security.Principal;
import java.util.List;
import java.util.Map;

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
	void submiteRequest(Map<String, String> recordd);
	void acceptRequest(Long id, Principal principal);
	void declineRequest(Long id); 

}
