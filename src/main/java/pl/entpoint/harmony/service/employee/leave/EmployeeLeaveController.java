package pl.entpoint.harmony.service.employee.leave;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.entpoint.harmony.entity.employee.EmployeeLeave;

import java.util.Map;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@RestController
@RequestMapping("/api/employee/leave")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class EmployeeLeaveController {
    final EmployeeLeaveService employeeLeaveService;

    @GetMapping("/{id}")
    public EmployeeLeave getEmployeeInfo(@PathVariable Long id){
        return employeeLeaveService.getEmployeeLeave(id);
    }

    @PatchMapping("/")
    public ResponseEntity<String> saveChange(@RequestBody Map<String, String> employeeLeave){
        employeeLeaveService.change(employeeLeave);
        return new ResponseEntity<>("Dane urlopowe zostały zapisane.", HttpStatus.OK);
    }
}
