package pl.entpoint.harmony.service.employee.leave;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.entpoint.harmony.entity.employee.EmployeeLeave;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@Repository
public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave, Long> {
}
