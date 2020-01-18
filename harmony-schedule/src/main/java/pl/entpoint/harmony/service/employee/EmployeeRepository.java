package pl.entpoint.harmony.service.employee;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.entpoint.harmony.models.employee.Employee;

/**
 * @author Mateusz Dąbek
 * @created 19/11/2019
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByPesel(long pesel);

}