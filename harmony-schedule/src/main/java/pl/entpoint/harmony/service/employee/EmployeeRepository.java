package pl.entpoint.harmony.service.employee;

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

    List<Employee> findByWorkStatus(WorkStatus status);
    List<Employee> findByWorkStatusNot(WorkStatus status);

    Optional<Employee> findByPesel(String pesel);

    Long countByWorkStatus(WorkStatus status);

}
