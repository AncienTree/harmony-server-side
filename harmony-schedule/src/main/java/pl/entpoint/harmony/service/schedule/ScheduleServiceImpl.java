package pl.entpoint.harmony.service.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.schedule.Schedule;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Mateusz Dąbek
 * @created 03/02/2020
 */

@Service
public class ScheduleServiceImpl implements ScheduleService {

    ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule getScheduleByDate(Date date) {
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
            throw new RuntimeException("Nie znaleziono grafiku pod ID - " + id);
        }
        schedule.setActive(active);
        schedule.setVisible(visible);
        
    	scheduleRepository.save(schedule);
    }
}
