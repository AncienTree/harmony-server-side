package pl.entpoint.harmony.rest;

import java.util.List;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pl.entpoint.harmony.entity.Employee;
import pl.entpoint.harmony.service.EmployeeService;

/**
 * @author Mateusz Dąbek
 * Created on Nov 21, 2019
 * m.dabek@entpoint.pl
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@Api(
	name="Employee API",
	description = "Do pobrania danych pracowników",
	stage = ApiStage.ALPHA
)
public class EmployeeController {
	
	@Autowired
	EmployeeService emplService;
	
	@GetMapping("/employees")
	@ApiMethod(description = "Do testów. Pobiera wszystkie dane pracowników!")
	public List<Employee> getListOfEmployees(){
		List<Employee> empl = emplService.getEmployees();
		return empl;
	}

	@GetMapping("/employee/{pesel}")
	@ApiMethod(description = "Pobiera pracownika po numerze pesel")
	public Employee getEmployeeByPesel(@ApiPathParam(name = "Pesel") @PathVariable long pesel) {
		return emplService.getEmployeeByPesel(pesel);
	}
	
	@GetMapping("/employee/hr/{pesel}")
	@ApiMethod(description = "sprawdza czy dany numer pesel jest w bazie danych")
	public boolean isEmplInDB(@PathVariable long pesel) {
		return emplService.isPeselInDB(pesel);
	}
	
}
