package pl.entpoint.harmony.service.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.schedule.Schedule;
import pl.entpoint.harmony.entity.schedule.ScheduleSummary;
import pl.entpoint.harmony.entity.settings.DayOff;
import pl.entpoint.harmony.service.schedule.summary.ScheduleSummaryService;
import pl.entpoint.harmony.service.settings.dayOff.DayOffService;
import pl.entpoint.harmony.service.settings.monthHours.MonthHoursService;
import pl.entpoint.harmony.util.exception.schedule.ScheduleExistException;
import pl.entpoint.harmony.util.exception.schedule.ScheduleNotFoundException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Mateusz Dąbek
 * @created 03/02/2020
 */

@Service
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	private ScheduleRepository scheduleRepository;
    @Autowired
    private ScheduleSummaryService scheduleSummaryService;
    @Autowired
    private DayOffService dayOffService;
    @Autowired
    private MonthHoursService monthHoursService;

    @Override
    public Schedule getScheduleByDate(LocalDate date) {
        Schedule schedule = scheduleRepository.findByScheduleDate(date);
        schedule.setRbh(addMonthHours(schedule));
        schedule.setDayOffs(addDayOff(schedule));
        return schedule;
    }

	@Override
    public List<Schedule> getActiveSchedules() {
        List<Schedule> schedules = scheduleRepository.findAllActive();

        for (Schedule schedule: schedules) {
            schedule.setRbh(addMonthHours(schedule));
            schedule.setDayOffs(addDayOff(schedule));
        }
        return schedules;
    }
    
    @Override
    public List<Schedule> getMySchedules(String login) {
    	List<ScheduleSummary> schedulesSum = scheduleSummaryService.getEmployeeSchedules(login); 
    	List<Schedule> schedules = getSchedules();
    	List<Schedule> returnSchedules = new ArrayList<>();

    	for (ScheduleSummary summ : schedulesSum) {    		
    		returnSchedules.addAll(schedules.stream()
                    .filter(x -> summ.getScheduleDate().isEqual(x.getScheduleDate()))
                    .collect(Collectors.toList()));
		}

        Collections.sort(returnSchedules);

        return returnSchedules;
    }

    private List<DayOff> addDayOff(Schedule schedule){
        LocalDate start = schedule.getScheduleDate();
        LocalDate end = LocalDate.of(start.getYear(), start.getMonthValue(), start.lengthOfMonth());

        return dayOffService.getDayOffBetweenDats(start, end);
    }

    @Override
    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    private int addMonthHours(Schedule schedule){
        return monthHoursService.checkMonthHours(schedule.getScheduleDate());
    }

    @Override
    public boolean isActive(LocalDate date) {
        Schedule schedule = scheduleRepository.findByScheduleDate(date);

        return schedule.isActive();
    }

    @Override
    public void changeStatus(Long id, boolean active, boolean visible) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException(id));

        schedule.setActive(active);
        schedule.setVisible(visible);
        
    	scheduleRepository.save(schedule);
    }

    @Override
    public void createSchedule(LocalDate date) {
        Optional<Schedule> optionalSchedule = Optional.ofNullable(scheduleRepository.findByScheduleDate(date));
        if (optionalSchedule.isEmpty()) {
            Schedule schedule = new Schedule();
            schedule.setScheduleDate(date);

            scheduleRepository.save(schedule);
        } else {
            throw new ScheduleExistException();
        }

    }
}
