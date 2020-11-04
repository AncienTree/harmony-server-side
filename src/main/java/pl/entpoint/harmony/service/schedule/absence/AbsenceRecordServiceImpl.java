package pl.entpoint.harmony.service.schedule.absence;

import java.security.Principal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.pojo.controller.AbsencePojo;
import pl.entpoint.harmony.entity.schedule.AbsenceRecord;
import pl.entpoint.harmony.entity.schedule.enums.AbsenceStatus;
import pl.entpoint.harmony.entity.settings.UserSection;
import pl.entpoint.harmony.service.employee.EmployeeService;
import pl.entpoint.harmony.service.schedule.record.ScheduleRecordService;
import pl.entpoint.harmony.service.settings.userSection.UserSectionService;
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
	private final UserSectionService userSectionService;

	@Override
	public List<AbsenceRecord> getAll() {
		return absenceRepository.findByStatus(AbsenceStatus.NOWY);
	}

	@Override
	public List<AbsenceRecord> getEmployeeRequests(Long id) {
		Employee employee = employeeService.getEmployeeNotDecrypted(id);
		return absenceRepository.findByEmployeeAndStatus(employee, AbsenceStatus.NOWY);
	}

	@Override
	public List<AbsenceRecord> getSectionRequests(Long id) {
		UserSection section = userSectionService.getSectionById(id);
		List<AbsenceRecord> records = getAll();
		List<AbsenceRecord> filteredRecords = new ArrayList<>();

		for (AbsenceRecord record: records) {
			if( record.getEmployee().getEmployeeDetails().getUserSection().equals(section.getName()) ) {
				filteredRecords.add(record);
			}
		}
		return filteredRecords;
	}

	@Override
	public List<AbsenceRecord> getMyRequests(String year, String opt, Principal principal) {
		Employee employee = userService.getUserByLogin(principal.getName()).getEmployee();
		LocalDate start = LocalDate.of(Integer.parseInt(year), Month.JANUARY, 1);
		LocalDate end = LocalDate.of(Integer.parseInt(year), Month.DECEMBER, 31);
		switch (opt) {
			case "all":
				return absenceRepository.findByEmployeeAndWorkDateBetween(employee, start, end);
			case "new":
				return absenceRepository.findByEmployeeAndStatusAndWorkDateBetween(employee, AbsenceStatus.NOWY, start, end);
			case "declined":
				return absenceRepository.findByEmployeeAndStatusAndWorkDateBetween(employee, AbsenceStatus.ODRZUCONY, start, end);
			case "accepted":
				return absenceRepository.findByEmployeeAndStatusAndWorkDateBetween(employee, AbsenceStatus.ZAAKCEPTOWANY, start, end);
			default:
				throw new IllegalArgumentException("Błędny parametr opt");
		}
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
				rec.setStatus(AbsenceStatus.NOWY);

				absenceRepository.save(rec);
			}
		}
	}

	@Override
	public void acceptRequest(Long id) {
		AbsenceRecord recordAccepted  = absenceRepository.findById(id)
				.orElseThrow(() -> new AbsenceRequestNotFoundException(id));

		recordAccepted.setStatus(AbsenceStatus.ZAAKCEPTOWANY);
		// Stworzenie rekordu do grafiku z urlopem
		scheduleRecordService.acceptAbsence(recordAccepted);

		absenceRepository.save(recordAccepted);
	}

	//TODO zmienić principal na pole z klasy audyt
	@Override
	public void declineRequest(Long id, String description) {
		AbsenceRecord recordAccepted  = absenceRepository.findById(id)
				.orElseThrow(() -> new AbsenceRequestNotFoundException(id));

		recordAccepted.setStatus(AbsenceStatus.ODRZUCONY);
		recordAccepted.setText(description);

		absenceRepository.save(recordAccepted);
	}

	@Override
	public void deleteRequest(Long id) {
		AbsenceRecord recordDeclined = absenceRepository.findById(id)
				.orElseThrow(() -> new AbsenceRequestNotFoundException(id));

		absenceRepository.delete(recordDeclined);
	}
}
