package pl.entpoint.harmony.service.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.entpoint.harmony.entity.schedule.Schedule;

import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 03/02/2020
 */

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Schedule findByScheduleDate(LocalDate date);
}
