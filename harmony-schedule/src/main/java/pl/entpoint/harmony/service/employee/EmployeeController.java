package pl.entpoint.harmony.service.employee;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;
import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.service.user.UserService;
import pl.entpoint.harmony.util.BCrypt;
import pl.entpoint.harmony.util.BlowfishEncryption;
import pl.entpoint.harmony.util.LoginConverter;

/**
 * @author Mateusz Dąbek
 * @created 21/11/2019
 */

//TODO zmienić RequestMapping na poddomenę Employee
@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class EmployeeController {

    private EmployeeService employeeService;
    private UserService userService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, UserService userService) {
        this.employeeService = employeeService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createNewUser(@RequestBody Map<String, String> body) throws Exception {
        Date birthday = Date.valueOf(body.get("birthday").substring(0, 10));
        Date start =Date.valueOf(body.get("startWorkDate").substring(0, 10));
        User theUser = new User(
                LoginConverter.createLogin(body.get("firstName"), body.get("lastName")),
                BCrypt.encrypt(body.get("pesel")));
        theUser.setId(0L);
        log.info("Wczytano pesel: " + body.get("pesel"));
        Employee theEmp = new Employee(
                body.get("firstName"),
                body.get("lastName"),
                BlowfishEncryption.encrypt(body.get("pesel")),
                body.get("sex"),
                birthday,
                body.get("position"),
                body.get("contractPosition"),
                WorkStatus.WORK,
                body.get("contractType"),
                body.get("basicUnit"),
                body.get("unit"),
                start,
                start);
        theUser.setEmployee(theEmp);        
        userService.createUser(theUser);
        return theUser;
    }

    @GetMapping("/all")
    public List<Employee> getListOfEmployees() {
        return employeeService.getEmployeesByStatusIsNot(WorkStatus.NOT_WORK);
    }

    @GetMapping("/headcount")
    public List<Employee> getListOfWorkingEmployee() {
        return employeeService.getEmployeesByStatusIsNot(WorkStatus.NOT_WORK);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id){
        return employeeService.getEmployee(id);
    }

    @PatchMapping("/")
    public ResponseEntity<String> saveChange(@RequestBody Map<String, String> employee){
        employeeService.change(employee);
        return new ResponseEntity<>("Dane pracownika zostały zapisane.", HttpStatus.OK);
    }

    @GetMapping("/hr/{pesel}")
    public Optional<Employee> getEmployeeByPesel(@PathVariable String pesel) {
        return Optional.ofNullable(employeeService.getEmployeeByPesel(pesel));
    }

    @PreAuthorize("hasRole('ROLE_HR') or hasRole('ROLE_ADMIN')")
    @GetMapping("/hr/{pesel}/isAvailable")
    public boolean isEmplInDB(@PathVariable String pesel) {
        return employeeService.isPeselInDB(pesel);
    }

    @GetMapping("/counter")
    public Map<String, Long> counter() {
        return employeeService.countByWorkStatus();
    }
}
