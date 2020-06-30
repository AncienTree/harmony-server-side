package pl.entpoint.harmony.service.employee.old;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.entpoint.harmony.entity.employee.OldEmployee;

/**
 * @author Mateusz Dąbek
 * @created 30.06.2020
 */

@Repository
public interface OldEmployeeRepository extends JpaRepository<OldEmployee, Long> {
    OldEmployee findByPesel(String pesel);
}
