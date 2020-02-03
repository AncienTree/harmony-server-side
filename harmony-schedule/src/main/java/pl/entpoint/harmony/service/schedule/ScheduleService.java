package pl.entpoint.harmony.service.schedule;

import pl.entpoint.harmony.entity.schedule.Schedule;

import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 03/02/2020
 */
public interface ScheduleService {

    Schedule getScheduleByDate(LocalDate date);
}
