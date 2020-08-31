package pl.entpoint.harmony.service.schedule.record;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;
import pl.entpoint.harmony.entity.schedule.enums.ScheduleType;

/**
 * @author Mateusz Dąbek
 * @created 19/11/2019
 */

@Repository
public interface ScheduleRecordRepository extends JpaRepository<ScheduleRecord, Long> {

    List<ScheduleRecord> findByWorkDateAndEmployee(LocalDate date, Employee employee);
    List<ScheduleRecord> findByWorkDateAndTypes(LocalDate workDate, ScheduleType types);
}
