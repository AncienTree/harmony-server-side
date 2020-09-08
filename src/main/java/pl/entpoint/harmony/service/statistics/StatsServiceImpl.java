package pl.entpoint.harmony.service.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.dto.Stats;
import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.service.schedule.record.ScheduleRecordService;
import pl.entpoint.harmony.service.settings.monthHours.MonthHoursService;
import pl.entpoint.harmony.service.user.UserService;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

/**
 * @author Mateusz Dąbek
 * @created 06.09.2020
 */

@Service
public class StatsServiceImpl implements StatsService {

	private static LocalDate date = LocalDate.now();
	
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
		Stats stats = new Stats();
		
		Optional<User> optUser = Optional.ofNullable(userService.getUserByLogin(principal.getName()));
		if(optUser.isPresent()) {
			user = optUser.get();
		} else {
			throw new IllegalArgumentException("Nie znaleziono użytkownika pod takim loginem: " + principal.getName());
		}		
		
		stats.setLeaves(user.getEmployee().getEmployeeLeave().getTotal());
		stats.setCurrentMonthHours(mHoursService.checkMonthHours(date));
		

//		private int currentMonthWorkedHours;
//	    private int currentMonthHours;
//		private int currentMonthLeaves;
//		private int absences;
//		private int currentMonthAbsences

		return stats;
	}



	@Override
	public Stats getStats(Long id) {
		return null;
	}
}
