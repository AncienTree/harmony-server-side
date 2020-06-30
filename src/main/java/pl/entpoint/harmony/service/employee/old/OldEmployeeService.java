package pl.entpoint.harmony.service.employee.old;


import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.employee.OldEmployee;

import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 30.06.2020
 */

public interface OldEmployeeService {
    List<OldEmployee> getAll();
    boolean findByPesel(String pesel);

    void add(Employee employee);
    void remove(OldEmployee employee);
}
