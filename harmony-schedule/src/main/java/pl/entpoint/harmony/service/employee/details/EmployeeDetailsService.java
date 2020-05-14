package pl.entpoint.harmony.service.employee.details;

import pl.entpoint.harmony.entity.employee.EmployeeDetails;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

public interface EmployeeDetailsService {
    EmployeeDetails getEmployeeDetails(Long id);
    void change(EmployeeDetails employeeDetails);
}
