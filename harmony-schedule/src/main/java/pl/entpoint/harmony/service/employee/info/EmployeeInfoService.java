package pl.entpoint.harmony.service.employee.info;

import pl.entpoint.harmony.entity.employee.EmployeeInfo;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

public interface EmployeeInfoService {
    EmployeeInfo getEmployeeInfo(Long id);
    void change(EmployeeInfo employeeInfo);
}
