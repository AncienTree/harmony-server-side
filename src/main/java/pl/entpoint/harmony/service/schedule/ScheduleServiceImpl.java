package pl.entpoint.harmony.service.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.schedule.Schedule;
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

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule getScheduleByDate(LocalDate date) {
        return scheduleRepository.findByScheduleDate(date);
    }

    @Override
    public List<Schedule> getActiveSchedules() {
        return scheduleRepository.findAllActive();
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
}
