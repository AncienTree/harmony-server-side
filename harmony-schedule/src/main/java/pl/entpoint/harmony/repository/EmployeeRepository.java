package pl.entpoint.harmony.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.entpoint.harmony.entity.Employee;

/**
 * @author Mateusz Dąbek
 * Created on Nov 19, 2019
 * m.dabek@entpoint.pl
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	public Optional<Employee> findByPesel(long pesel);

}
