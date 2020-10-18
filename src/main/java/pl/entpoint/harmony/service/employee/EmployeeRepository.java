package pl.entpoint.harmony.service.employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;

/**
 * @author Mateusz Dąbek
 * @created 19/11/2019
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByPesel(String pesel);
    List<Employee> findByWorkStatus(WorkStatus status);
    List<Employee> findByWorkStatusNot(WorkStatus status);
    List<Employee> findByPositionAndWorkStatusNot(String position,WorkStatus status);
    List<Employee> findByWorkStatusAndStartWorkDateLessThanEqual(WorkStatus status, LocalDate date);
    Long countByWorkStatus(WorkStatus status);
}
