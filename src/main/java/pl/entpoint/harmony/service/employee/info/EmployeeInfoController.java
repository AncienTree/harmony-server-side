package pl.entpoint.harmony.service.employee.info;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.entpoint.harmony.entity.employee.EmployeeInfo;
import pl.entpoint.harmony.entity.pojo.controller.InfoPojo;

import java.util.Map;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@RestController
@RequestMapping("/api/employee/info")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Api(tags = "Employee Information Controller")
public class EmployeeInfoController {

    final EmployeeInfoService employeeInfoService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Get employee information by id.", nickname = "Get employee information by id.")
    @ApiImplicitParam(name = "id", value = "Employee information id", required = true, dataType = "Long", paramType = "path")
    public EmployeeInfo getEmployeeInfo(@PathVariable Long id){
        return employeeInfoService.getEmployeeInfo(id);
    }

    @PatchMapping("/")
    @ApiOperation(value = "Change employee information.", nickname = "Change employee information.")
    public ResponseEntity<String> saveChange(@RequestBody InfoPojo employeeInfo){
        employeeInfoService.change(employeeInfo);
        return new ResponseEntity<>("Dane informacyjne zostały zapisane.", HttpStatus.OK);
    }
}
