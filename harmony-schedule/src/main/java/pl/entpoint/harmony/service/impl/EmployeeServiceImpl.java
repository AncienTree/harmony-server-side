package pl.entpoint.harmony.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.Employee;
import pl.entpoint.harmony.repository.EmployeeRepository;
import pl.entpoint.harmony.service.EmployeeService;

/**
 * @author Mateusz Dąbek
 * Created on Nov 20, 2019
 * m.dabek@entpoint.pl
 */

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepository emplRepository;
	
	@Override
	public Employee getEmployeeByPesel(long pesel) {
		return emplRepository.findByPesel(pesel);
	}
	
	

}
