package pl.entpoint.harmony.service.employee.info;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.entpoint.harmony.entity.employee.EmployeeInfo;

import java.util.Map;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@RestController
@RequestMapping("/api/employee/info")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class EmployeeInfoController {

    final EmployeeInfoService employeeInfoService;

    @GetMapping("/{id}")
    public EmployeeInfo getEmployeeInfo(@PathVariable Long id){
        return employeeInfoService.getEmployeeInfo(id);
    }

    @PatchMapping("/")
    public ResponseEntity<String> saveChange(@RequestBody Map<String, String> employeeInfo){
        employeeInfoService.change(employeeInfo);
        return new ResponseEntity<>("Dane informacyjne zostały zapisane.", HttpStatus.OK);
    }
}
