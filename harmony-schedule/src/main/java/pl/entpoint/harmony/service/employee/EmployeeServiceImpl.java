package pl.entpoint.harmony.service.employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;
import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.service.user.UserService;
import pl.entpoint.harmony.util.BlowfishEncryption;
import pl.entpoint.harmony.util.exception.employee.EmployeeNotFoundException;

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
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        try {
            employee.setPesel(BlowfishEncryption.decrypt(employee.getPesel()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
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
            throw new EmployeeNotFoundException();
        }
        return empl;
    }

    @Override
    public void change(Map<String, String> employee) {
        Long id = Long.parseLong(employee.get("id"));
        Optional<Employee> optionalEmployee = Optional.ofNullable(employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id)));
        Employee empl = null;
        if (optionalEmployee.isPresent()){
            empl = optionalEmployee.get();
        }
        // Zmiany
        assert empl != null;

        empl.setEmail(employee.get("email"));
        empl.setPosition(employee.get("position"));
        empl.setContractPosition(employee.get("contractPosition"));
        empl.setContractType(employee.get("contractType"));
        empl.setBasicUnit(employee.get("basicUnit"));
        empl.setUnit(employee.get("unit"));
        employeeRepository.save(empl);
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

    @Override
    public List<Employee> getEmployeesByStatus(WorkStatus status) {
        return employeeRepository.findByWorkStatus(status);
    }

    @Override
    public List<Employee> getEmployeesByStatusIsNot(WorkStatus status) {
        return employeeRepository.findByWorkStatusNot(status);
    }

    @Override
    public Map<String, Long> countByWorkStatus() {
        Map<String, Long> counter = new HashMap<>();
        counter.put("working", employeeRepository.countByWorkStatus(WorkStatus.WORK));
        counter.put("l4", employeeRepository.countByWorkStatus(WorkStatus.L4));
        counter.put("suspend", employeeRepository.countByWorkStatus(WorkStatus.SUSPENDED));
        counter.put("not_working", employeeRepository.countByWorkStatus(WorkStatus.NOT_WORK));

        return counter;
    }
}
