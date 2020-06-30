package pl.entpoint.harmony.service.employee.old;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.employee.OldEmployee;
import pl.entpoint.harmony.service.employee.EmployeeService;

import java.util.List;
import java.util.Optional;

/**
 * @author Mateusz Dąbek
 * @created 30.06.2020
 */

@Service
public class OldEmployeeServiceImpl implements OldEmployeeService{

    OldEmployeeRepository oldEmployeeRepository;
    EmployeeService employeeService;

    @Autowired
    public OldEmployeeServiceImpl(OldEmployeeRepository oldEmployeeRepository, EmployeeService employeeService) {
        this.oldEmployeeRepository = oldEmployeeRepository;
        this.employeeService = employeeService;
    }

    @Override
    public List<OldEmployee> getAll() {
        return oldEmployeeRepository.findAll();
    }

    @Override
    public boolean findByPesel(String pesel) {
        Optional<OldEmployee> oldEmployee = Optional.ofNullable(oldEmployeeRepository.findByPesel(pesel));
        return oldEmployee.isPresent();
    }

    @Override
    public void add(Employee employee) {
        Optional<OldEmployee> oldEmployee = Optional.ofNullable(oldEmployeeRepository.findByPesel(employee.getPesel()));
        if(oldEmployee.isPresent()){
          throw new RuntimeException("Pracownik jest już w bazie zwolnionych pracowników");
        }
        OldEmployee tempEmployee = new OldEmployee(employee, employee.getEmployeeDetails());

        oldEmployeeRepository.save(tempEmployee);
        employeeService.fireEmployee(employee);
    }

    @Override
    public void remove(OldEmployee employee) {
        oldEmployeeRepository.delete(employee);
    }
}
