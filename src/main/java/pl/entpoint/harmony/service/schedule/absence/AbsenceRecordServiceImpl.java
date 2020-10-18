package pl.entpoint.harmony.service.schedule.absence;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.pojo.controller.AbsencePojo;
import pl.entpoint.harmony.entity.schedule.AbsenceRecord;
import pl.entpoint.harmony.service.employee.EmployeeService;
import pl.entpoint.harmony.service.schedule.record.ScheduleRecordService;
import pl.entpoint.harmony.service.user.UserService;
import pl.entpoint.harmony.util.exception.schedule.AbsenceRequestNotFoundException;

/**
 * @author Mateusz Dąbek
 * @created 22 wrz 2020
 * 
 */

@Service
@AllArgsConstructor
public class AbsenceRecordServiceImpl implements AbsenceRecordService {

	private final AbsenceRecordRepository absenceRepository;
	private final UserService userService;
	private final EmployeeService employeeService;
	private final ScheduleRecordService scheduleRecordService;

	@Override
	public List<AbsenceRecord> getAll() {
		return absenceRepository.findByVisibleTrue();
	}

	@Override
	public List<AbsenceRecord> getEmployeeRequests(Long id) {
		Employee temp = employeeService.getEmployeeNotDecrypted(id);
		return absenceRepository.findByEmployeeAndVisibleTrue(temp);
	}

	@Override
	public List<AbsenceRecord> getMyRequests(Principal principal) {
		Employee tmpEmployee = userService.getUserByLogin(principal.getName()).getEmployee();

		return absenceRepository.findByEmployeeAndVisibleTrue(tmpEmployee);
	}

	@Override
	public boolean isAlreadyInDb(Employee employee, LocalDate date) {
		Optional<AbsenceRecord> opt = Optional.ofNullable(absenceRepository.findByEmployeeAndWorkDate(employee, date));
		return opt.isPresent();
	}

	@Override
	public void submitRequest(List<AbsencePojo> record, String login) {
		Employee tempEmployee = userService.getUserByLogin(login).getEmployee();
		Employee employee = employeeService.getEmployeeNotDecrypted(tempEmployee.getId());

		for (AbsencePojo absencePojo : record) {
			if(!isAlreadyInDb(employee, absencePojo.getWorkDate())) {
				AbsenceRecord rec = new AbsenceRecord();
				rec.setEmployee(employee);
				rec.setWorkDate(absencePojo.getWorkDate());

				absenceRepository.save(rec);
			}
		}
	}

	@Override
	public void acceptRequest(Long id, Principal principal) {
		AbsenceRecord recordAccepted  = absenceRepository.findById(id)
				.orElseThrow(() -> new AbsenceRequestNotFoundException(id));

		recordAccepted.setAcceptedBy(principal.getName());
		recordAccepted.setVisible(false);

		// Stworzenie rekordu do grafiku z urlopem
		scheduleRecordService.acceptAbsence(recordAccepted);

		absenceRepository.save(recordAccepted);
	}

	@Override
	public void declineRequest(Long id) {
		AbsenceRecord recordDeclined = absenceRepository.findById(id)
				.orElseThrow(() -> new AbsenceRequestNotFoundException(id));

		absenceRepository.delete(recordDeclined);
	}
}
