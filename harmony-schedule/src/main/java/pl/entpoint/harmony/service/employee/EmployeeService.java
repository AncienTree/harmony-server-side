package pl.entpoint.harmony.service.employee;

import java.util.List;
import java.util.Map;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;

/**
 * @author Mateusz Dąbek
 * @created 11/11/2019
 */

public interface EmployeeService {

    List<Employee> getEmployees();
    List<Employee> getEmployeesByStatus(WorkStatus status);
    List<Employee> getEmployeesByStatusIsNot(WorkStatus status);
    Employee getEmployee(Long id);
    Employee getEmployeeByPesel(String pesel);
    boolean isPeselInDB(String pesel);
    String getFullNameByLogin(String login);
    Map<String, Long> countByWorkStatus();
}
