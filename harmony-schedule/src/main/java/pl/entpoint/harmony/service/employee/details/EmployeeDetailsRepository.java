package pl.entpoint.harmony.service.employee.details;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.entpoint.harmony.entity.employee.EmployeeDetails;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Long> {
}
