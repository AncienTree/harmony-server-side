package pl.entpoint.harmony.service.schedule.absence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.AbsenceRecord;
import pl.entpoint.harmony.entity.schedule.enums.AbsenceStatus;

/**
 * @author Mateusz Dąbek
 * @created 22 wrz 2020
 * 
 */

@Repository
public interface AbsenceRecordRepository extends JpaRepository<AbsenceRecord, Long> {
	AbsenceRecord findByEmployeeAndWorkDate(Employee employee, LocalDate date);
	List<AbsenceRecord> findByStatus(AbsenceStatus status);
	List<AbsenceRecord> findByStatusAndWorkDateBetween(AbsenceStatus status, LocalDate start, LocalDate end, Sort sort);
	List<AbsenceRecord> findByEmployeeAndStatus(Employee employee, AbsenceStatus status);
	List<AbsenceRecord> findByEmployeeAndStatusAndWorkDateBetween(Employee employee, AbsenceStatus status, LocalDate start, LocalDate end);
	List<AbsenceRecord> findByEmployeeAndWorkDateBetween(Employee employee, LocalDate start, LocalDate end);
}
