package pl.entpoint.harmony.service.employee;

import java.util.List;
import java.util.Map;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;
import pl.entpoint.harmony.entity.model.SimpleEmployee;

/**
 * @author Mateusz Dąbek
 * @created 11/11/2019
 */

public interface EmployeeService {

    List<Employee> getEmployees();
    List<Employee> getEmployeesByStatus(WorkStatus status);
    List<Employee> getEmployeesByStatusIsNot(WorkStatus status);
    List<SimpleEmployee> getWorkingEmployeesByPosition(String position);
    Employee getEmployee(Long id);
    Employee getEmployeeByPesel(String pesel);

    void change(Map<String, String> employee);
    boolean isPeselInDB(String pesel);
    String getFullNameByLogin(String login);
    Map<String, Long> countByWorkStatus();
}
