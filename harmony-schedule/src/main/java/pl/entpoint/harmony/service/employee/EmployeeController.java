package pl.entpoint.harmony.service.employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;
import pl.entpoint.harmony.entity.model.SimpleEmployee;
import pl.entpoint.harmony.entity.model.view.HrTable;

/**
 * @author Mateusz Dąbek
 * @created 21/11/2019
 */

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/")
    public ResponseEntity<String> createNewUser(@RequestBody Map<String, String> body) {
    	employeeService.newEmployee(body);
        return new ResponseEntity<>("Utworzono nowego pracownika: " + body.get("firstName") + " " + 
                body.get("lastName"), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Employee> getListOfEmployees() {
        return employeeService.getEmployeesByStatusIsNot(WorkStatus.NOT_WORK);
    }

    @GetMapping("/headcount")
    public List<Employee> getListOfWorkingEmployee() {
        return employeeService.getEmployeesByStatusIsNot(WorkStatus.NOT_WORK);
    }

    @GetMapping("/position/{position}")
    public List<SimpleEmployee> getListOfWorkingEmployeeByPosition(@PathVariable String position) {
        return employeeService.getWorkingEmployeesByPosition(position);
    }

    @GetMapping("/personal")
    public List<HrTable> getPersonalDate() {
        return employeeService.getPersonalDate();
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
