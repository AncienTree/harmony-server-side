package pl.entpoint.harmony.service.schedule.absence;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.AbsenceRecord;
import pl.entpoint.harmony.service.employee.EmployeeService;
import pl.entpoint.harmony.service.schedule.record.ScheduleRecordService;
import pl.entpoint.harmony.service.user.UserService;

/**
 * @author Mateusz Dąbek
 * @created 22 wrz 2020
 * 
 */

@Service
public class AbsenceRecordServiceImpl implements AbsenceRecordService {
	
	private final AbsenceRecordRepository absenceRepository;
	private final UserService userService;
	private final EmployeeService emplService;
	private final ScheduleRecordService scheduleRecordService;
	
	@Autowired
	public AbsenceRecordServiceImpl(AbsenceRecordRepository absenceRepository, UserService userService,
			ScheduleRecordService scheduleRecordService, EmployeeService emplService) {
		this.emplService = emplService;
		this.absenceRepository = absenceRepository;
		this.userService = userService;
		this.scheduleRecordService = scheduleRecordService;
	}

	@Override
	public List<AbsenceRecord> getAll() {
		return absenceRepository.findByVisibleTrue();
	}

	@Override
	public List<AbsenceRecord> getEmployeeRequests(Long id) {
		Employee temp = emplService.getEmployeeNotDecrypted(id);
		return absenceRepository.findByEmployeeAndVisibleTrue(temp);
	}

	@Override
	public List<AbsenceRecord> getMyRequests(Principal principal) {
		Employee tmpEmployee = userService.getUserByLogin(principal.getName()).getEmployee();
		
		return absenceRepository.findByEmployeeAndVisibleTrue(tmpEmployee);
	}

	@Override
	public void submiteRequest(Map<String, String> record) {
		Employee empl = emplService.getEmployeeNotDecrypted(Long.parseLong(record.get("employee")));
		AbsenceRecord rec = new AbsenceRecord();
		rec.setEmployee(empl);
		rec.setWorkDate(LocalDate.parse(record.get("workDate")));
		
		absenceRepository.save(rec);
	}

	@Override
	public void acceptRequest(Long id, Principal principal) {
		AbsenceRecord recordAccepted;
		Optional<AbsenceRecord> optRecord = absenceRepository.findById(id);
		if (optRecord.isPresent()) {
			recordAccepted = optRecord.get();
		} else {
			throw new IllegalArgumentException("Wniosek urlopowy nie istnieje w bazie danych: " + id);
		}

		recordAccepted.setAcceptedBy(principal.getName());
		recordAccepted.setVisible(false);
		
		// Stworzenie rekordu do grafiku z urlopem
		scheduleRecordService.accepteAbsence(recordAccepted);
		
		absenceRepository.save(recordAccepted);
	}

	@Override
	public void declineRequest(Long id) {
		AbsenceRecord recordDeclined;
		Optional<AbsenceRecord> optRecord = absenceRepository.findById(id);
		if (optRecord.isPresent()) {
			recordDeclined = optRecord.get();
		} else {
			throw new IllegalArgumentException("Wniosek urlopowy nie istnieje w bazie danych: " + id);
		}
		
		absenceRepository.delete(recordDeclined);
	}

}
