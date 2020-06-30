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
import pl.entpoint.harmony.entity.employee.OldEmployee;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;
import pl.entpoint.harmony.entity.dto.SimpleEmployee;
import pl.entpoint.harmony.entity.dto.view.HrTable;
import pl.entpoint.harmony.service.employee.old.OldEmployeeService;

/**
 * @author Mateusz Dąbek
 * @created 21/11/2019
 */

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final OldEmployeeService oldEmployeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, OldEmployeeService oldEmployeeService) {
        this.employeeService = employeeService;
        this.oldEmployeeService = oldEmployeeService;
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
    public boolean[] getEmployeeByPesel(@PathVariable String pesel) {
        boolean[] peselDB = new boolean[2];
        Optional<Employee> empl = Optional.ofNullable(employeeService.getEmployeeByPesel(pesel));

        peselDB[0] = empl.isPresent();
        peselDB[1] = oldEmployeeService.findByPesel(pesel);
        return peselDB;
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
