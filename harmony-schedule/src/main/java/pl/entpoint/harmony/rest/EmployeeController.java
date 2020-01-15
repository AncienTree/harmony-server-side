package pl.entpoint.harmony.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pl.entpoint.harmony.entity.Employee;
import pl.entpoint.harmony.entity.User;
import pl.entpoint.harmony.service.EmployeeService;
import pl.entpoint.harmony.service.UserService;
import pl.entpoint.harmony.util.BCrypt;
import pl.entpoint.harmony.util.LoginConverter;

/**
 * @author Mateusz Dąbek
 * Created on Nov 21, 2019
 * m.dabek@entpoint.pl
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class EmployeeController {
	
	@Autowired
	EmployeeService emplService;

	@Autowired
	private UserService userService;
		
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	@PostMapping("/employee")
	public User createNewUser(@RequestBody Map<String, String> body) {		
	System.out.println("------------------------------------------------------");
		System.out.println(body);
		System.out.println("------------------------------------------------------");
		LocalDate bd = LocalDate.parse(body.get("birthday").substring(0, 10), dateFormatter);
	    LocalDate sw = LocalDate.parse(body.get("startWorkDate").substring(0, 10), dateFormatter);
		User theUser = new User(
				LoginConverter.createLogin(body.get("firstName"), body.get("lastName")),
				BCrypt.decrypt(body.get("pesel")));
		theUser.setId(0);
		log.info("Wczytano pesel: " + body.get("pesel"));
		Employee theEmp = new Employee(
				body.get("firstName"), 
				body.get("lastName"),
				Long.parseLong(body.get("pesel")),
				body.get("sex"),
				bd,
				body.get("position"),
				body.get("contractPosition"),
				"Pracuje",
				body.get("contractType"),
				body.get("basicUnit"),
				body.get("unit"),
				sw,
				sw);
		theUser.setEmployee(theEmp);
		userService.createUser(theUser);
		
		return theUser;
	}
	
	@GetMapping("/employees")
	public List<Employee> getListOfEmployees(){
		return emplService.getEmployees();
	}

	@GetMapping("/employee/{pesel}")
	public Employee getEmployeeByPesel(@PathVariable long pesel) {
		return emplService.getEmployeeByPesel(pesel);
	}
	
	@GetMapping("/employee/hr/{pesel}")
	public boolean isEmplInDB(@PathVariable long pesel) {
		return emplService.isPeselInDB(pesel);
	}
	
}
