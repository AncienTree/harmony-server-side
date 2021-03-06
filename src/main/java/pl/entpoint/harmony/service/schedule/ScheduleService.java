package pl.entpoint.harmony.service.schedule;

import pl.entpoint.harmony.entity.schedule.Schedule;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 03/02/2020
 */
public interface ScheduleService {
    Schedule getScheduleByDate(LocalDate date);
    List<Schedule> getActiveSchedules();
    List<Schedule> getSchedules();
    List<Schedule> getMySchedules(String login);
    boolean isActive(LocalDate date);
    void changeStatus(Long id, boolean active, boolean visible);
    void createSchedule(LocalDate date);
}
