package pl.entpoint.harmony.service.schedule;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;
import pl.entpoint.harmony.entity.schedule.enums.ScheduleStatus;

/**
 * @author Mateusz Dąbek
 * @created 19/11/2019
 */

@Repository
public interface ScheduleRecordRepository extends JpaRepository<ScheduleRecord, Long> {

    List<ScheduleRecord> findByStatus(ScheduleStatus status);

    List<ScheduleRecord> findByWorkDateBetween(LocalDate startDate, LocalDate endDate);

    List<ScheduleRecord> findByEmployee(Employee employee);

    List<ScheduleRecord> findByWorkDate(LocalDate date);

    List<ScheduleRecord> findByWorkDateAndEmployee(LocalDate date, Employee employee);

    List<ScheduleRecord> findByWorkDateBetweenAndStatus(LocalDate startDate, LocalDate endDate, ScheduleStatus status);

    List<ScheduleRecord> findByWorkDateBetweenAndEmployee(LocalDate startDate, LocalDate endDate, Employee employee);

}
