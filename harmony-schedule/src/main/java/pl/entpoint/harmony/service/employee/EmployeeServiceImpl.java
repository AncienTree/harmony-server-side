package pl.entpoint.harmony.service.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.employee.Employee;

/**
 * @author Mateusz Dąbek
 * @created 20/11/2019
 */


@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(Long id) {
        Optional<Employee> result = employeeRepository.findById(id);

        Employee empl;
        if (result.isPresent()) {
            empl = result.get();
        } else {
            throw new RuntimeException("Nie znaleziono użytkownika pod takim id: " + id);
        }
        return empl;
    }

    @Override
    public Employee getEmployeeByPesel(Long pesel) {
        Optional<Employee> result = employeeRepository.findByPesel(pesel);

        Employee empl;
        if (result.isPresent()) {
            empl = result.get();
        } else {
            throw new RuntimeException("Nie znaleziono użytkownika pod takim numerem PESEL");
        }
        return empl;
    }

    @Override
    public boolean isPeselInDB(Long pesel) {
        Optional<Employee> result = employeeRepository.findByPesel(pesel);

        return result.isPresent();
    }
}
