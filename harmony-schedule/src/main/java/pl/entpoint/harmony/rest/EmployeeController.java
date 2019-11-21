package pl.entpoint.harmony.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.entpoint.harmony.entity.Employee;
import pl.entpoint.harmony.service.EmployeeService;

/**
 * @author Mateusz Dąbek
 * Created on Nov 21, 2019
 * m.dabek@entpoint.pl
 */

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	EmployeeService emplService;
	
	@GetMapping("/employees")
	public List<Employee> getListOfEmployees(){
		List<Employee> empl = emplService.getEmployees();
		return empl;
	}

}
