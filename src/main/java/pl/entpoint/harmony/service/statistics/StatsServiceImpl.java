package pl.entpoint.harmony.service.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.dto.Stats;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.service.schedule.record.ScheduleRecordService;
import pl.entpoint.harmony.service.settings.monthHours.MonthHoursService;
import pl.entpoint.harmony.service.user.UserService;
import pl.entpoint.harmony.util.exception.employee.EmployeeNotFoundException;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

/**
 * @author Mateusz Dąbek
 * @created 06.09.2020
 */

@Service
public class StatsServiceImpl implements StatsService {

	private static final LocalDate DATE = LocalDate.now();
	
	private final UserService userService;
	private final ScheduleRecordService recordService;
	private final MonthHoursService mHoursService;
	
	
	@Autowired
	public StatsServiceImpl(UserService userService, ScheduleRecordService recordService,
			MonthHoursService mHoursService) {
		this.userService = userService;
		this.recordService = recordService;
		this.mHoursService = mHoursService;
	}

	@Override
	public Stats getMyStats(Principal principal) {
		User user;
		
		Optional<User> optUser = Optional.ofNullable(userService.getUserByLogin(principal.getName()));
		if(optUser.isPresent()) {
			user = optUser.get();
		} else {
			throw new IllegalArgumentException("Nie znaleziono użytkownika pod takim loginem: " + principal.getName());
		}		
		
		return getStats(user.getEmployee());
	}

	@Override
	public Stats getSomeoneStats(Long id) {
		User user;
		Optional<User> optUser = Optional.ofNullable(userService.getUser(id));
		if(optUser.isPresent()) {
			user = optUser.get();
		} else {
			throw new EmployeeNotFoundException(id);
		}	
		
		return getStats(user.getEmployee());
	}
	
	private Stats getStats(Employee employee) {
		Stats stats = new Stats();
		stats.setLeaves(employee.getEmployeeLeave().getTotal());
		stats.setCurrentMonthHours(mHoursService.checkMonthHours(DATE));
		stats.setCurrentMonthWorkedHours(recordService.getCurrentMonthStatus(employee, "work", DATE));
		stats.setCurrentMonthAbsences(recordService.getCurrentMonthStatus(employee,"absences", DATE));
		stats.setCurrentMonthLeaves(recordService.getCurrentMonthStatus(employee,"leave", DATE));
		stats.setAbsences(recordService.getCurrentYearStatus(employee, "absences", DATE));

		return stats;
	}
}
