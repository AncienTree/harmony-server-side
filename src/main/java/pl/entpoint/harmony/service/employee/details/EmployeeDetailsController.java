package pl.entpoint.harmony.service.employee.details;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.entpoint.harmony.entity.employee.EmployeeDetails;
import pl.entpoint.harmony.entity.pojo.controller.DetailsPojo;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@RestController
@RequestMapping("/api/employee/details")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Api(tags = "Employee Details Controller")
public class EmployeeDetailsController {

    final EmployeeDetailsService employeeDetailsService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Get employee details information by id.", nickname = "Get employee details information by id.")
    @ApiImplicitParam(name = "id", value = "Employee details id", required = true, dataType = "Long", paramType = "path")
    public EmployeeDetails getContactDetails(@PathVariable Long id){
        return employeeDetailsService.getEmployeeDetails(id);
    }

    @PatchMapping("/")
    @ApiOperation(value = "Change employee details information.", nickname = "Change employee details information.")
    public ResponseEntity<String> saveChange(@RequestBody DetailsPojo employeeDetails){
        employeeDetailsService.change(employeeDetails);
        return new ResponseEntity<>("Dane dodatkowe zostały zapisane.", HttpStatus.OK);
    }
}
