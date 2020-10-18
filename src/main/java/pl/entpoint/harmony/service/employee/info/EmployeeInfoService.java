package pl.entpoint.harmony.service.employee.info;

import pl.entpoint.harmony.entity.employee.EmployeeInfo;
import pl.entpoint.harmony.entity.pojo.controller.InfoPojo;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

public interface EmployeeInfoService {
    EmployeeInfo getEmployeeInfo(Long id);
    void change(InfoPojo employeeInfo);
}
