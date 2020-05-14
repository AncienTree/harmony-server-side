package pl.entpoint.harmony.service.employee.leave;

import pl.entpoint.harmony.entity.employee.EmployeeLeave;

import javax.validation.Valid;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

public interface EmployeeLeaveService {
    EmployeeLeave getEmployeeLeave(Long id);
    void change(EmployeeLeave employeeLeave);
}
