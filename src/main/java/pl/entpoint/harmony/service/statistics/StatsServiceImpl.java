package pl.entpoint.harmony.service.statistics;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.pojo.Stats;
import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.service.schedule.record.ScheduleRecordService;
import pl.entpoint.harmony.service.settings.monthHours.MonthHoursService;
import pl.entpoint.harmony.service.user.UserService;
import pl.entpoint.harmony.util.exception.user.UserNotFoundException;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

/**
 * @author Mateusz Dąbek
 * @created 06.09.2020
 */

@Service
@AllArgsConstructor
public class StatsServiceImpl implements StatsService {
	private static final LocalDate DATE = LocalDate.now();
	
	private final UserService userService;
	private final ScheduleRecordService recordService;
	private final MonthHoursService mHoursService;

	@Override
	public Stats getMyStats(Principal principal) {
		User user = Optional.ofNullable(userService.getUserByLogin(principal.getName()))
				.orElseThrow(() -> new UserNotFoundException(principal.getName()));

		if (user.getLogin().equals("administrator") || user.getLogin().equals("kadry")) {
			return new Stats();
		}

		return getStats(user.getEmployee());
	}

	@Override
	public Stats getSomeoneStats(Long id) {
		User user = Optional.ofNullable(userService.getUser(id))
				.orElseThrow(UserNotFoundException::new);

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
