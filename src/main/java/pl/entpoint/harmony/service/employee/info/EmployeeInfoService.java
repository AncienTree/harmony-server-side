package pl.entpoint.harmony.service.employee.info;

import pl.entpoint.harmony.entity.employee.EmployeeInfo;

import java.util.Map;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

public interface EmployeeInfoService {
    EmployeeInfo getEmployeeInfo(Long id);
    void change(Map<String, String> employeeInfo);
}
