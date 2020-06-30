package pl.entpoint.harmony.service.employee.old;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mateusz Dąbek
 * @created 30.06.2020
 */

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class OldEmployeeController {

    OldEmployeeService employeeService;

    @Autowired
    public OldEmployeeController(OldEmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
