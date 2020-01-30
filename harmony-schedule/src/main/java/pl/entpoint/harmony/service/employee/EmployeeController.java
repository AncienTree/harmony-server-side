package pl.entpoint.harmony.service.employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;
import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.service.user.UserService;
import pl.entpoint.harmony.util.BCrypt;
import pl.entpoint.harmony.util.LoginConverter;

/**
 * @author Mateusz Dąbek
 * @created 21/11/2019
 */


@RestController
@RequestMapping("/api")
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
                BCrypt.encrypt(body.get("pesel")));
        theUser.setId(0L);
        log.info("Wczytano pesel: " + body.get("pesel"));
        Employee theEmp = new Employee(
                body.get("firstName"),
                body.get("lastName"),
                Long.parseLong(body.get("pesel")),
                body.get("sex"),
                bd,
                body.get("position"),
                body.get("contractPosition"),
                WorkStatus.WORK,
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
    public List<Employee> getListOfEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/employee/{pesel}")
    public Optional<Employee> getEmployeeByPesel(@PathVariable long pesel) {
        return Optional.ofNullable(employeeService.getEmployeeByPesel(pesel));
    }

    @PreAuthorize("hasRole('ROLE_HR') or hasRole('ROLE_ADMIN')")
    @GetMapping("/employee/hr/{pesel}")
    public boolean isEmplInDB(@PathVariable long pesel) {
        return employeeService.isPeselInDB(pesel);
    }

}
