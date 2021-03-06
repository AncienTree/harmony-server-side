package pl.entpoint.harmony.service.schedule.absence;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.pojo.controller.AbsencePojo;
import pl.entpoint.harmony.entity.schedule.AbsenceRecord;
import pl.entpoint.harmony.entity.schedule.enums.AbsenceStatus;

/**
 * @author Mateusz Dąbek
 * @created 22 wrz 2020
 * 
 */

public interface AbsenceRecordService {
	List<AbsenceRecord> getAllByStatus(String year, String opt);
	List<AbsenceRecord> getEmployeeRequests(Long id);
	List<AbsenceRecord> getSectionRequests(Long id);
	List<AbsenceRecord> getMyRequests(String year, String opt, Principal principal);
	boolean isAlreadyInDb(Employee employee, LocalDate date);
	void submitRequest(List<AbsencePojo> record, String login);
	void acceptRequest(Long id);
	void declineRequest(Long id, String description);
	void deleteRequest(Long id);

}
