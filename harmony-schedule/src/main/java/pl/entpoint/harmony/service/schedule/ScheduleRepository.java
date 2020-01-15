package pl.entpoint.harmony.service.schedule;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.entpoint.harmony.models.employee.Employee;
import pl.entpoint.harmony.models.schedule.Schedule;
import pl.entpoint.harmony.models.schedule.enums.ScheduleStatus;

/**
 * @author Mateusz Dąbek
 * @created 19/11/2019
 */

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByStatus(ScheduleStatus status);

    List<Schedule> findByWorkDateBetween(LocalDate startDate, LocalDate endDate);

    List<Schedule> findByEmployee(Employee employee);

    List<Schedule> findByWorkDate(LocalDate date);

    List<Schedule> findByWorkDateAndEmployee(LocalDate date, Employee employee);

    List<Schedule> findByWorkDateBetweenAndStatus(LocalDate startDate, LocalDate endDate, ScheduleStatus status);

    List<Schedule> findByWorkDateBetweenAndEmployee(LocalDate startDate, LocalDate endDate, Employee employee);

}
