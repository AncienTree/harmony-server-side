package pl.entpoint.harmony.service.schedule;

import java.sql.Date;
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

    List<ScheduleRecord> findByWorkDateBetween(Date startDate, Date endDate);

    List<ScheduleRecord> findByEmployee(Employee employee);

    List<ScheduleRecord> findByWorkDate(Date date);

    List<ScheduleRecord> findByWorkDateAndEmployee(Date date, Employee employee);

    List<ScheduleRecord> findByWorkDateBetweenAndStatus(Date startDate, Date endDate, ScheduleStatus status);

    List<ScheduleRecord> findByWorkDateBetweenAndEmployee(Date startDate, Date endDate, Employee employee);

}
