package pl.entpoint.harmony.service.schedule.absence;

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
	List<AbsenceRecord> findByEmployee(Employee employee);
}
