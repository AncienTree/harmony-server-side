package pl.entpoint.harmony.service.employee;

import java.util.List;

import pl.entpoint.harmony.entity.employee.Employee;

/**
 * @author Mateusz Dąbek
 * @created 11/11/2019
 */

public interface EmployeeService {

    List<Employee> getEmployees();

    Employee getEmployee(Long id);

    Employee getEmployeeByPesel(Long pesel);

    boolean isPeselInDB(Long pesel);

    String getFullNameByLogin(String login);

}
