package pl.entpoint.harmony.service.employee.details;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.entpoint.harmony.entity.employee.EmployeeDetails;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@RestController
@RequestMapping("/api/employee/details")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class EmployeeDetailsController {

    EmployeeDetailsService employeeDetailsService;

    @Autowired
    public EmployeeDetailsController(EmployeeDetailsService employeeDetailsService) {
        this.employeeDetailsService = employeeDetailsService;
    }

    @GetMapping("/{id}")
    public EmployeeDetails getContactDetails(@PathVariable Long id){
        return employeeDetailsService.getEmployeeDetails(id);
    }

    @PatchMapping("/")
    public ResponseEntity<String> saveChange(@RequestBody EmployeeDetails employeeDetails){
        employeeDetailsService.change(employeeDetails);
        return new ResponseEntity<>("Dane dodatkowe zostały zapisane.", HttpStatus.OK);
    }
}
