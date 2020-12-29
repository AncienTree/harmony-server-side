package pl.entpoint.harmony.service.employee;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.pojo.controller.EmployeePojo;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;
import pl.entpoint.harmony.entity.pojo.SimpleEmployee;
import pl.entpoint.harmony.entity.pojo.dbview.HrTable;

/**
 * @author Mateusz Dąbek
 * @created 21/11/2019
 */

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Api(tags = "Employee Controller")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/")
    @ApiOperation(value = "Get all working employees.", nickname = "Get all working employees.")
    public List<Employee> getListOfEmployees() {
        return employeeService.getEmployeesByStatusIsNot(WorkStatus.NOT_WORK);
    }

    @GetMapping("/me")
    @ApiOperation(value = "Get me as Simple Employee.", nickname = "Get me as Simple Employee.")
    public SimpleEmployee getMe(Principal principal) {
        return employeeService.getMeAsSimpleEmployee(principal.getName());
    }

    @GetMapping("/personal")
    @ApiOperation(value = "Get all data for personal table.", nickname = "Get all data for personal table.")
    public List<HrTable> getPersonalDate() {
        return employeeService.getPersonalDate();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get employee by id.", nickname = "Get employee by id.")
    @ApiImplicitParam(name = "id", value = "Employee id", required = true, dataType = "Long", paramType = "path")
    public Employee getEmployee(@PathVariable Long id){
        return employeeService.getEmployeeDecrypted(id);
    }

    @GetMapping("/counter")
    @ApiOperation(value = "Get number of employee by work status.", nickname = "Get number of employee by work status.")
    public Map<String, Long> counter() {
        return employeeService.countByWorkStatus();
    }

    @GetMapping("/position/{position}")
    @ApiOperation(value = "Get employees by position.", nickname = "Get employees by position.")
    @ApiImplicitParam(name = "position", value = "Position", required = true, dataType = "String", paramType = "path")
    public List<SimpleEmployee> getListOfWorkingEmployeeByPosition(@PathVariable String position) {
        return employeeService.getWorkingEmployeesByPosition(position);
    }

    @PreAuthorize("hasRole('ROLE_HR') or hasRole('ROLE_ADMIN')")
    @GetMapping("/hr/{pesel}/isAvailable")
    @ApiOperation(value = "Check if PESEL is in database.", nickname = "Check if PESEL is in database.")
    @ApiImplicitParam(name = "pesel", value = "PESEL", required = true, dataType = "String", paramType = "path")
    public boolean isPeselInDB(@PathVariable String pesel) {
        return employeeService.isPeselInDB(pesel);
    }

    @GetMapping("/hr/{pesel}")
    @ApiOperation(value = "Get employee by PESEL.", nickname = "Get employee by PESEL.")
    @ApiImplicitParam(name = "pesel", value = "PESEL", required = true, dataType = "String", paramType = "path")
    public SimpleEmployee getEmployeeByPesel(@PathVariable String pesel) {
        return new SimpleEmployee(employeeService.getEmployeeByPesel(pesel));
    }

    @PostMapping("/")
    @ApiOperation(value = "Create new employee.", nickname = "Create new employee.")
    @ApiImplicitParam(name = "employee", value = "Employee body", required = true, dataType = "EmployeePojo", paramType = "body")
    public ResponseEntity<String> createNewUser(@RequestBody EmployeePojo employee) {
        employeeService.newEmployee(employee);
        return new ResponseEntity<>("Utworzono nowego pracownika: " + employee.getFirstName() + " " +
                employee.getLastName(), HttpStatus.CREATED);
    }

    @PostMapping("/fire")
    @ApiOperation(value = "Fire employee.", nickname = "Fire employee.")
    @ApiImplicitParam(name = "id", value = "Employee id", required = true, dataType = "Long", paramType = "body")
    public ResponseEntity<String> fire(@RequestBody Long id) {
        employeeService.fireEmployee(id);
        
        return new ResponseEntity<>("Użytkownik został zwolniony", HttpStatus.OK);
    }

    @PostMapping("/restore")
    @ApiOperation(value = "Restore employee.", nickname = "Restore employee.")
    @ApiImplicitParam(name = "id", value = "Employee id", required = true, dataType = "Long", paramType = "body")
    public ResponseEntity<String> restore(@RequestBody Long id) {
        employeeService.restoreEmployee(id);

        return new ResponseEntity<>("Użytkownik został przywrócony", HttpStatus.OK);
    }

    @PatchMapping("/")
    @ApiOperation(value = "Change employee.", nickname = "Change employee.")
    @ApiImplicitParam(name = "employee", value = "Employee body", required = true, dataType = "EmployeePojo", paramType = "body")
    public ResponseEntity<String> saveChange(@RequestBody EmployeePojo employee){
        employeeService.change(employee);
        return new ResponseEntity<>("Dane pracownika zostały zapisane.", HttpStatus.OK);
    }
}
