package pl.entpoint.harmony.service.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.schedule.Schedule;

import java.time.LocalDate;
import java.util.List;

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
    public Schedule getScheduleByDate(LocalDate date) {
        return scheduleRepository.findByScheduleDate(date);
    }

    @Override
    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }
}
