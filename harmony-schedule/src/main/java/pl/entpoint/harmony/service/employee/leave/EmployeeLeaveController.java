package pl.entpoint.harmony.service.employee.leave;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.entpoint.harmony.entity.employee.EmployeeLeave;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@RestController
@RequestMapping("/api/employee/leave")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class EmployeeLeaveController {
    EmployeeLeaveService employeeLeaveService;

    @Autowired
    public EmployeeLeaveController(EmployeeLeaveService employeeLeaveService) {
        this.employeeLeaveService = employeeLeaveService;
    }

    @GetMapping("/{id}")
    public EmployeeLeave getEmployeeInfo(@PathVariable Long id){
        return employeeLeaveService.getEmployeeLeave(id);
    }

    @PatchMapping("/")
    public ResponseEntity<String> saveChange(@RequestBody EmployeeLeave employeeLeave){
        employeeLeaveService.change(employeeLeave);
        return new ResponseEntity<>("Dane urlopowe zostały zapisane.", HttpStatus.OK);
    }
}