package pl.entpoint.harmony.service.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.schedule.Schedule;
import pl.entpoint.harmony.entity.settings.DayOff;
import pl.entpoint.harmony.service.settings.dayOff.DayOffService;
import pl.entpoint.harmony.service.settings.monthHours.MonthHoursService;
import pl.entpoint.harmony.util.exception.schedule.ScheduleExisteException;
import pl.entpoint.harmony.util.exception.schedule.ScheduleNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Mateusz Dąbek
 * @created 03/02/2020
 */

@Service
public class ScheduleServiceImpl implements ScheduleService {

    final ScheduleRepository scheduleRepository;
    final DayOffService dayOffService;
    final MonthHoursService monthHoursService;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, DayOffService dayOffService, MonthHoursService monthHoursService) {
        this.scheduleRepository = scheduleRepository;
        this.dayOffService = dayOffService;
        this.monthHoursService = monthHoursService;
    }

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
    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }
    
    @Override
    public void changeStatus(Long id, boolean active, boolean visible) {
        Optional<Schedule> result = scheduleRepository.findById(id);
        
        Schedule schedule;
        if(result.isPresent()) {
        	schedule = result.get();
        } else {
            throw new ScheduleNotFoundException(id);
        }
        schedule.setActive(active);
        schedule.setVisible(visible);
        
    	scheduleRepository.save(schedule);
    }

    @Override
    public void createSchedule(LocalDate date) {
        Optional<Schedule> result = Optional.ofNullable(scheduleRepository.findByScheduleDate(date));
        Schedule schedule;
        if(!result.isPresent()) {
            schedule = new Schedule(date);
        } else {
            throw new ScheduleExisteException(date);
        }
        scheduleRepository.save(schedule);
    }

    private int addMonthHours(Schedule schedule){
        return monthHoursService.checkMonthHours(schedule.getScheduleDate());
    }

    private List<DayOff> addDayOff(Schedule schedule){
        LocalDate start = schedule.getScheduleDate();
        LocalDate end = LocalDate.of(start.getYear(), start.getMonthValue(), start.lengthOfMonth());

        return dayOffService.getDayOffBetweenDats(start, end);
    }
}
