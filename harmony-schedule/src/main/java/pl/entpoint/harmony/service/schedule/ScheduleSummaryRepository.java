package pl.entpoint.harmony.service.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleSummary;

import java.sql.Date;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 30/01/2020
 */

@Repository
public interface ScheduleSummaryRepository extends JpaRepository<ScheduleSummary, Long> {

    ScheduleSummary findByScheduleDateAndEmployee(Date date, Employee employee);

    List<ScheduleSummary> findByScheduleDate(Date date);
}
