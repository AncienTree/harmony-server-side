package pl.entpoint.harmony.repository;

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
	
	public Employee findByPesel(long pesel);

}
