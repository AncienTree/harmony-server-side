package pl.entpoint.harmony.service.employee;

import java.time.LocalDate;
import java.util.*;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.pojo.controller.EmployeePojo;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;
import pl.entpoint.harmony.entity.pojo.SimpleEmployee;
import pl.entpoint.harmony.entity.pojo.dbview.HrTable;
import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.service.user.UserService;
import pl.entpoint.harmony.util.BCrypt;
import pl.entpoint.harmony.util.EncryptionData;
import pl.entpoint.harmony.util.LoginConverter;
import pl.entpoint.harmony.util.exception.employee.EmployeeNotFoundException;
import pl.entpoint.harmony.util.exception.employee.EmployeeNotWorkingException;
import pl.entpoint.harmony.util.exception.employee.EmployeeWorkingException;

/**
 * @author Mateusz Dąbek
 * @created 20/11/2019
 */


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final HrTableViewRepository hrTableViewRepository;
    private final UserService userService;

    @Override
    public Employee getEmployeeDecrypted(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        try {
            employee.setPesel(EncryptionData.decrypt(employee.getPesel()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public Employee getEmployeeNotDecrypted(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public Employee getEmployeeByPesel(String pesel) {
        return employeeRepository.findByPesel(EncryptionData.encrypt(pesel))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public SimpleEmployee getMeAsSimpleEmployee(String login) {
        User tempUser = userService.getUserByLogin(login);

        return new SimpleEmployee(tempUser.getEmployee());
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<SimpleEmployee> getWorkingEmployeesByPosition(String position) {
        List<Employee> employees = employeeRepository.findByPositionAndWorkStatusNotOrderByLastName(position, WorkStatus.NOT_WORK);
        List<SimpleEmployee> simpleEmployees = new ArrayList<>();

        for (Employee employee: employees) {
            simpleEmployees.add(new SimpleEmployee(employee));
        }
        return simpleEmployees;
    }

    @Override
    public List<Employee> getEmployeesByStatus(WorkStatus status) {
        return employeeRepository.findByWorkStatus(status);
    }

    @Cacheable(value = "listOfEmployee")
    @Override
    public List<Employee> getEmployeesByStatusIsNot(WorkStatus status) {
        return employeeRepository.findByWorkStatusNotOrderByLastName(status);
    }

    @Override
    public List<HrTable> getPersonalDate() {
        return hrTableViewRepository.findAll();
    }

        
    @Override
	public List<Employee> getEmployeesByStatusAndStartWork(WorkStatus status, LocalDate date) {
		return employeeRepository.findByWorkStatusAndStartWorkDateLessThanEqual(status, date);
	}

    @Override
    @CacheEvict(value = "listOfEmployee", allEntries = true)
    public void change(EmployeePojo employeePojo) {
        Employee employee = employeeRepository.findById(employeePojo.getId())
                .orElseThrow(() -> new EmployeeNotFoundException(employeePojo.getId()));

        // Zmiany
        employee.setEmail(employeePojo.getEmail());
        employee.setFullTime(employeePojo.getEtat());
        employee.setPosition(employeePojo.getPosition());
        employee.setContractPosition(employeePojo.getContractPosition());
        employee.setContractType(employeePojo.getContractType());
        employee.setBasicUnit(employeePojo.getBasicUnit());
        employee.setUnit(employeePojo.getUnit());
        employeeRepository.save(employee);
    }

    @Override
    public boolean isPeselInDB(String pesel) {
        Optional<Employee> employee = employeeRepository.findByPesel(EncryptionData.encrypt(pesel));

        return employee.isPresent();
    }

    @Override
    public String getFullNameByLogin(String login) {
        if (login.equals("administrator") || login.equals("hr")) {
            return "SYSTEM";
        }
        User tempUser = userService.getUserByLogin(login);
        Employee tempEmployee = getEmployeeDecrypted(tempUser.getEmployee().getId());

        return tempEmployee.getFirstName() + " " + tempEmployee.getLastName();
    }

    @Override
    public Long numberOfEmployee() {
        return employeeRepository.countByWorkStatus(WorkStatus.WORK);
    }

    @Override
    @Cacheable(value = "employeesCounter")
    public Map<String, Long> countByWorkStatus() {
        Map<String, Long> counter = new HashMap<>();
        counter.put("working", employeeRepository.countByWorkStatus(WorkStatus.WORK));
        counter.put("l4", employeeRepository.countByWorkStatus(WorkStatus.L4));
        counter.put("suspend", employeeRepository.countByWorkStatus(WorkStatus.SUSPENDED));
        counter.put("not_working", employeeRepository.countByWorkStatus(WorkStatus.NOT_WORK));

        return counter;
    }
    
    @Override
    @Caching(evict = {
            @CacheEvict(value = "listOfEmployee", allEntries = true),
            @CacheEvict(value = "employeesCounter", allEntries = true),
    })
    public void newEmployee(EmployeePojo body) {
        User user = new User(
                LoginConverter.createLogin(body.getFirstName(), body.getLastName()),
                BCrypt.encrypt(body.getPesel()));
        Employee employee = new Employee(body);
        user.setEmployee(employee);

        userService.createUser(user);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "listOfEmployee", allEntries = true),
            @CacheEvict(value = "employeesCounter", allEntries = true),
    })
    public void fireEmployee(Long id) {
    	Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

    	if(employee.getWorkStatus() == WorkStatus.NOT_WORK){
    	    throw new EmployeeNotWorkingException();
        }

    	employee.fire();
    	employee.getEmployeeDetails().fire();
    	employee.getEmployeeInfo().fire();
    	employee.getEmployeeLeave().fire();
    	employee.getContactDetails().fire();
    	
    	employeeRepository.save(employee);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "listOfEmployee", allEntries = true),
            @CacheEvict(value = "employeesCounter", allEntries = true),
    })
    public void restoreEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        if(employee.getWorkStatus() != WorkStatus.NOT_WORK){
            throw new EmployeeWorkingException();
        }
        employee.restore();
        employee.getEmployeeDetails().restore();

        employeeRepository.save(employee);
    }
}
