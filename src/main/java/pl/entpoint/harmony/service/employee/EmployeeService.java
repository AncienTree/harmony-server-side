package pl.entpoint.harmony.service.employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.pojo.controller.EmployeePojo;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;
import pl.entpoint.harmony.entity.pojo.SimpleEmployee;
import pl.entpoint.harmony.entity.pojo.dbview.HrTable;

/**
 * @author Mateusz Dąbek
 * @created 11/11/2019
 */

public interface EmployeeService {
    Employee getEmployeeDecrypted(Long id);
    Employee getEmployeeNotDecrypted(Long id);
    Employee getEmployeeByPesel(String pesel);
    List<Employee> getEmployees();
    List<Employee> getEmployeesByStatus(WorkStatus status);
    List<Employee> getEmployeesByStatusIsNot(WorkStatus status);
    List<Employee> getEmployeesByStatusAndStartWork(WorkStatus status, LocalDate date);
    List<SimpleEmployee> getWorkingEmployeesByPosition(String position);
    List<HrTable> getPersonalDate();
    Map<String, Long> countByWorkStatus();
    boolean isPeselInDB(String pesel);
    String getFullNameByLogin(String login);
    void newEmployee(EmployeePojo body);
    void change(EmployeePojo employee);
    void fireEmployee(Long id);
    void restoreEmployee(Long id);
}
