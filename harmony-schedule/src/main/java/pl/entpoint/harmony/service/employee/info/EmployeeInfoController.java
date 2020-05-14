package pl.entpoint.harmony.service.employee.info;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.entpoint.harmony.entity.employee.EmployeeDetails;
import pl.entpoint.harmony.entity.employee.EmployeeInfo;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@RestController
@RequestMapping("/api/employee/info")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class EmployeeInfoController {

    EmployeeInfoService employeeInfoService;

    @Autowired
    public EmployeeInfoController(EmployeeInfoService employeeInfoService) {
        this.employeeInfoService = employeeInfoService;
    }

    @GetMapping("/{id}")
    public EmployeeInfo getEmployeeInfo(@PathVariable Long id){
        return employeeInfoService.getEmployeeInfo(id);
    }

    @PatchMapping("/")
    public ResponseEntity<String> saveChange(@RequestBody EmployeeInfo employeeInfo){
        employeeInfoService.change(employeeInfo);
        return new ResponseEntity<>("Dane informacyjne zostały zapisane.", HttpStatus.OK);
    }
}
