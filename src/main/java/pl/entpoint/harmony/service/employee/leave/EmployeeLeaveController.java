package pl.entpoint.harmony.service.employee.leave;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.entpoint.harmony.entity.employee.EmployeeLeave;
import pl.entpoint.harmony.entity.pojo.controller.LeavePojo;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@RestController
@RequestMapping("/api/employee/leave")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Api(tags = "Employee Leave Controller")
public class EmployeeLeaveController {
    final EmployeeLeaveService employeeLeaveService;

    @GetMapping("/{id}")

    @ApiOperation(value = "Get employee leaves id.", nickname = "Get employee leaves by id.")
    @ApiImplicitParam(name = "id", value = "Employee leaves id", required = true, dataType = "Long", paramType = "path")
    public EmployeeLeave getEmployeeInfo(@PathVariable Long id){
        return employeeLeaveService.getEmployeeLeave(id);
    }

    @PutMapping("/")
    @ApiOperation(value = "Change employee leaves.", nickname = "Change employee leaves.")
    public ResponseEntity<String> saveChange(@RequestBody LeavePojo employeeLeave){
        employeeLeaveService.change(employeeLeave);
        return new ResponseEntity<>("Dane urlopowe zostały zapisane.", HttpStatus.OK);
    }
}
