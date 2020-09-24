package pl.entpoint.harmony.service.schedule.summary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;
import pl.entpoint.harmony.entity.pojo.EmployeeScheduleList;
import pl.entpoint.harmony.entity.pojo.SimpleEmployee;
import pl.entpoint.harmony.entity.schedule.ScheduleSummary;
import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.service.employee.EmployeeService;
import pl.entpoint.harmony.service.schedule.ScheduleService;
import pl.entpoint.harmony.service.user.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Mateusz Dąbek
 * @created 30/01/2020
 */

@Service
public class ScheduleSummaryServiceimpl implements ScheduleSummaryService {

    final ScheduleSummaryRepository scheduleSummaryRepository;
    final ScheduleService scheduleService;
    final EmployeeService employeeService;
    final UserService userService;

    @Autowired
    public ScheduleSummaryServiceimpl(ScheduleSummaryRepository scheduleSummaryRepository,
			EmployeeService employeeService, ScheduleService scheduleService, UserService userService) {
		this.userService = userService;
		this.scheduleSummaryRepository = scheduleSummaryRepository;
		this.employeeService = employeeService;
		this.scheduleService = scheduleService;
	}

    @Override
    public ScheduleSummary getScheduleByDateAndEmployee(LocalDate date, Employee employee) {
        return scheduleSummaryRepository.findByScheduleDateAndEmployee(date, employee);
    }

	@Override
    public List<ScheduleSummary> getScheduleByDate(LocalDate date) {
        return scheduleSummaryRepository.findByScheduleDate(date);
    }	

    @Override
	public List<ScheduleSummary> getEmployeeSchedules(String login) {
    	return scheduleSummaryRepository.findByEmployee(loginToEntity(login).getEmployee());
	}

	

	@Override
    public void create(LocalDate date, Employee employee) {
        Optional<ScheduleSummary> scheduleSummary = Optional.ofNullable(getScheduleByDateAndEmployee(date, employee));
        ScheduleSummary summary;

        if(scheduleSummary.isPresent()) {
            throw new IllegalArgumentException("Dla danego użytkownika i daty istnieje już grafik.");
        } else {
            summary = new ScheduleSummary(employee, date.toString());
            scheduleSummaryRepository.save(summary);
        }
    }

    @Override
    public void massCreate(LocalDate date, List<Employee> employees) {
        List<ScheduleSummary> employeeList = new ArrayList<>();

        for (Employee employee: employees) {
            ScheduleSummary summary = new ScheduleSummary();
            summary.setEmployee(employee);
            summary.setScheduleDate(date);
            employeeList.add(summary);
        }
        scheduleSummaryRepository.saveAll(employeeList);
    }

	@Override
	public List<Employee> getEmployeeWithoutSchedule(LocalDate date) {
		LocalDate lastDay = date.withDayOfMonth(date.lengthOfMonth()); // Ostatni dzień miesiąca potrzebny do pobrania listy pracowników
		List<Employee> fullEmployees = employeeService.getEmployeesByStatusAndStartWork(WorkStatus.WORK, lastDay);
		List<ScheduleSummary> scheduleSummaries = scheduleSummaryRepository.findByScheduleDate(date);
		List<Employee> withoutSchedule = new ArrayList<>();
		
		for (Employee employee : fullEmployees) {
			boolean check = false;
			for (ScheduleSummary schedule : scheduleSummaries) {
                if (employee == schedule.getEmployee()) {
                    check = true;
                    break;
                }
			}
			if(!check) {
				withoutSchedule.add(employee);
			}
		}
		return withoutSchedule;
	}

	@Override
	public EmployeeScheduleList getListOfEmployees(LocalDate scheduleDate) {
		if (scheduleService.isActive(scheduleDate)) {
			List<ScheduleSummary> lists = getScheduleByDate(scheduleDate);
			List<SimpleEmployee> employees = new ArrayList<>();
			
			EmployeeScheduleList scheduleList = new EmployeeScheduleList();
			scheduleList.setScheduleDate(scheduleDate);
			
			// Pobieranie pracowników z wyciągu grafiku
			for (ScheduleSummary summary : lists) {
				employees.add(new SimpleEmployee(summary.getEmployee()));
			}
			
			scheduleList.setEmployees(employees);
		
			return scheduleList;
		} else {
			throw new IllegalArgumentException("Grafik jest nieaktywny");
		}		
	}

	@Override
	public ScheduleSummary getMySchedule(LocalDate localDate, String name) {
		Employee tempEmpl = loginToEntity(name).getEmployee();
		
		ScheduleSummary summ = getScheduleByDateAndEmployee(localDate, tempEmpl);
		summ.setSimpleEmployee(new SimpleEmployee(tempEmpl));
		return  summ;
	} 
	
	
	private User loginToEntity(String login) {
		User user;
    	Optional<User> opt = Optional.of(userService.getUserByLogin(login));
    	
    	if(opt.isPresent()) {
    		user = opt.get();
    	} else {
    		throw new UsernameNotFoundException("Nie znaleziono użytkownika o loginie " + login);
    	}
		return user;
	}	
}
