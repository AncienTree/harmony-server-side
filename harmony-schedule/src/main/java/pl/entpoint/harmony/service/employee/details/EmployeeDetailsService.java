package pl.entpoint.harmony.service.employee.details;

import pl.entpoint.harmony.entity.employee.EmployeeDetails;

import java.util.Map;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

public interface EmployeeDetailsService {
    EmployeeDetails getEmployeeDetails(Long id);
    void change(Map<String, String> employeeDetails);
}
