package pl.entpoint.harmony.service;

import java.util.List;

import pl.entpoint.harmony.entity.Employee;

/**
 * @author Mateusz Dąbek
 * Created on Nov 20, 2019
 * m.dabek@entpoint.pl
 */
public interface EmployeeService {
	
	List<Employee> getEmployees();
	
	public Employee getEmployeeByPesel(long pesel);
	
	public boolean isPeselInDB(long pesel);

}
