package pl.entpoint.harmony.service.employee.details;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.entpoint.harmony.entity.employee.EmployeeDetails;

import java.util.Map;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@RestController
@RequestMapping("/api/employee/details")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class EmployeeDetailsController {

    final EmployeeDetailsService employeeDetailsService;

    @GetMapping("/{id}")
    public EmployeeDetails getContactDetails(@PathVariable Long id){
        return employeeDetailsService.getEmployeeDetails(id);
    }

    @PatchMapping("/")
    public ResponseEntity<String> saveChange(@RequestBody Map<String, String> employeeDetails){
        employeeDetailsService.change(employeeDetails);
        return new ResponseEntity<>("Dane dodatkowe zostały zapisane.", HttpStatus.OK);
    }
}
