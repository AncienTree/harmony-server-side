package pl.entpoint.harmony.service.employee;

import java.sql.Date;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;
import pl.entpoint.harmony.entity.model.SimpleEmployee;
import pl.entpoint.harmony.entity.model.view.HrTable;
import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.service.user.UserService;
import pl.entpoint.harmony.util.BCrypt;
import pl.entpoint.harmony.util.BlowfishEncryption;
import pl.entpoint.harmony.util.LoginConverter;
import pl.entpoint.harmony.util.exception.employee.EmployeeNotFoundException;

/**
 * @author Mateusz Dąbek
 * @created 20/11/2019
 */


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final HrTableViewRepository hrTableViewRepository;
    private final UserService userService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, HrTableViewRepository hrTableViewRepository, UserService userService) {
        this.employeeRepository = employeeRepository;
        this.hrTableViewRepository = hrTableViewRepository;
        this.userService = userService;
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<SimpleEmployee> getWorkingEmployeesByPosition(String position) {
        List<Employee> employees = employeeRepository.findByPositionAndWorkStatusNot(position, WorkStatus.NOT_WORK);
        List<SimpleEmployee> simpleEmployees = new ArrayList<>();

        for (Employee empl: employees) {
            simpleEmployees.add(new SimpleEmployee(empl));
        }
        return simpleEmployees;
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
    public List<HrTable> getPersonalDate() {
        return hrTableViewRepository.findAll();
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
        empl.setEtat(Float.parseFloat(employee.get("etat")));
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
    public Map<String, Long> countByWorkStatus() {
        Map<String, Long> counter = new HashMap<>();
        counter.put("working", employeeRepository.countByWorkStatus(WorkStatus.WORK));
        counter.put("l4", employeeRepository.countByWorkStatus(WorkStatus.L4));
        counter.put("suspend", employeeRepository.countByWorkStatus(WorkStatus.SUSPENDED));
        counter.put("not_working", employeeRepository.countByWorkStatus(WorkStatus.NOT_WORK));

        return counter;
    }
    
    @Override
    public void newEmployee(Map<String, String> body) {
    	Date birthday = Date.valueOf(body.get("birthday").substring(0, 10));
        Date start = Date.valueOf(body.get("startWorkDate").substring(0, 10));
        
        User theUser = new User(
                LoginConverter.createLogin(body.get("firstName"), body.get("lastName")),
                BCrypt.encrypt(body.get("pesel")));
        theUser.setId(0L);
        Employee theEmp = new Employee(
                body.get("firstName"),
                body.get("lastName"),
                BlowfishEncryption.encrypt(body.get("pesel")),
                body.get("sex"),
                birthday,
                body.get("position"),
                body.get("contractPosition"),
                WorkStatus.WORK,
                body.get("contractType"),
                body.get("basicUnit"),
                body.get("unit"),
                start,
                start);
        theUser.setEmployee(theEmp);        
        userService.createUser(theUser);    	
    }
}