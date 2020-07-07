package pl.entpoint.harmony.service.schedule.summary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;
import pl.entpoint.harmony.entity.schedule.ScheduleSummary;
import pl.entpoint.harmony.service.employee.EmployeeService;

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
    final EmployeeService employeeService;

    @Autowired
    public ScheduleSummaryServiceimpl(ScheduleSummaryRepository scheduleSummaryRepository,
			EmployeeService employeeService) {
		this.scheduleSummaryRepository = scheduleSummaryRepository;
		this.employeeService = employeeService;
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
    public void create(LocalDate date, Employee employee) {
        Optional<ScheduleSummary> scheduleSummary = Optional.ofNullable(getScheduleByDateAndEmployee(date, employee));
        ScheduleSummary summary;

        if(scheduleSummary.isPresent()) {
            throw new RuntimeException("Dla danego użytkownika i daty istnieje już grafik.");
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
}
