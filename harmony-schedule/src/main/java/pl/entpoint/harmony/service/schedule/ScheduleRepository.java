package pl.entpoint.harmony.service.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.entpoint.harmony.entity.schedule.Schedule;

import java.sql.Date;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 03/02/2020
 */

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Schedule findByScheduleDate(Date date);

    @Query("SELECT s FROM Schedule s ORDER BY s.scheduleDate ASC")
    List<Schedule> findAll();
}
