package pl.entpoint.harmony.service.schedule.absence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.AbsenceRecord;

/**
 * @author Mateusz Dąbek
 * @created 22 wrz 2020
 * 
 */

@Repository
public interface AbsenceRecordRepository extends JpaRepository<AbsenceRecord, Long> {
	AbsenceRecord findByEmployeeAndWorkDate(Employee employee, LocalDate date);
	List<AbsenceRecord> findByEmployeeAndVisibleTrue(Employee employee);
	List<AbsenceRecord> findByVisibleTrue();
}
