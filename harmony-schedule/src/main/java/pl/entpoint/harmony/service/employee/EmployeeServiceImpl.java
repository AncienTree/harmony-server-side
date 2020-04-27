package pl.entpoint.harmony.service.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.service.user.UserService;
import pl.entpoint.harmony.util.BlowfishEncryption;

/**
 * @author Mateusz Dąbek
 * @created 20/11/2019
 */


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private UserService userService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, UserService userService) {
        this.employeeRepository = employeeRepository;
        this.userService = userService;
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
    public Employee getEmployeeByPesel(String pesel) {
        Optional<Employee> result = null;
		try {
			result = employeeRepository.findByPesel(BlowfishEncryption.encrypt(pesel));
		} catch (Exception e) {
			e.printStackTrace();
		}

        Employee empl;
        if (result.isPresent()) {
            empl = result.get();
        } else {
            throw new RuntimeException("Nie znaleziono użytkownika pod takim numerem PESEL");
        }
        return empl;
    }

    @Override
    public boolean isPeselInDB(String pesel) {
        Optional<Employee> result = null;
		try {
			result = employeeRepository.findByPesel(BlowfishEncryption.encrypt(pesel));
		} catch (Exception e) {
			e.printStackTrace();
		}

        return result.isPresent();
    }

    @Override
    public String getFullNameByLogin(String login) {
        User tempUser = userService.getUserByLogin(login);
        Employee tempEmployee = getEmployee(tempUser.getEmployee().getId());

        return tempEmployee.getFirstName() + " " + tempEmployee.getLastName();
    }
}
