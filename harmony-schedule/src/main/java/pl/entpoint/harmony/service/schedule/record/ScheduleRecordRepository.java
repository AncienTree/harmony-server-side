package pl.entpoint.harmony.service.schedule.record;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;

/**
 * @author Mateusz Dąbek
 * @created 19/11/2019
 */

@Repository
public interface ScheduleRecordRepository extends JpaRepository<ScheduleRecord, Long> {

    List<ScheduleRecord> findByWorkDateAndEmployee(Date date, Employee employee);

}
