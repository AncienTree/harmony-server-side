package pl.entpoint.harmony.service.schedule;

import pl.entpoint.harmony.entity.schedule.Schedule;

import java.sql.Date;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 03/02/2020
 */
public interface ScheduleService {

    Schedule getScheduleByDate(Date date);
    List<Schedule> getActiveSchedules();
    List<Schedule> getSchedules();
    void changeStatus(Long id, boolean active, boolean visible);
    Schedule createSchedule(Date date);
}