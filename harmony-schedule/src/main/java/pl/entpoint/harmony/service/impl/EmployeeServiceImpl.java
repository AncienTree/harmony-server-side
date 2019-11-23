package pl.entpoint.harmony.service.impl;

import java.util.List;
import java.util.Optional;

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
	public List<Employee> getEmployees() {
		return emplRepository.findAll();
	}
	
	@Override
	public Employee getEmployee(int id) {
		Optional<Employee> result = emplRepository.findById(id);
		
		Employee empl = null;
		if(result.isPresent()) {
			empl = result.get();
		} else {
			throw new RuntimeException("Nie znaleziono użytkownika pod takim id: " + id);
		}
		return empl;
	}

	@Override
	public Employee getEmployeeByPesel(long pesel) {
		Optional<Employee> result = emplRepository.findByPesel(pesel);
		
		Employee empl = null;
		if(result.isPresent()) {
			empl = result.get();
		} else {
			throw new RuntimeException("Nie znaleziono użytkownika pod takim numerem PESEL");
		}
		return empl;
	}

	@Override
	public boolean isPeselInDB(long pesel) {
		Optional<Employee> result = emplRepository.findByPesel(pesel);
		
		if(result.isPresent()) {
			return true;
		} else {
			return false;
		}
	}
}
