package pl.entpoint.harmony.service.employee.info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.entpoint.harmony.entity.employee.EmployeeInfo;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@Repository
public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Long> {
}
