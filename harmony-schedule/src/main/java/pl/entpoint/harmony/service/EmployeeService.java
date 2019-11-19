package pl.entpoint.harmony.service;

import pl.entpoint.harmony.entity.Employee;

/**
 * @author Mateusz Dąbek
 * Created on Nov 20, 2019
 * m.dabek@entpoint.pl
 */
public interface EmployeeService {
	
	public Employee getEmployeeByPesel(long pesel);

}
