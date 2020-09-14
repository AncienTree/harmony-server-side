package pl.entpoint.harmony.service.schedule.summary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleSummary;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 30/01/2020
 */

@Repository
public interface ScheduleSummaryRepository extends JpaRepository<ScheduleSummary, Long> {

    ScheduleSummary findByScheduleDateAndEmployee(LocalDate date, Employee employee);

    List<ScheduleSummary> findByScheduleDate(LocalDate date);
    List<ScheduleSummary> findByEmployee(Employee employee);
}
