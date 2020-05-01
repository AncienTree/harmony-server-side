package pl.entpoint.harmony.service.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleSummary;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Mateusz Dąbek
 * @created 30/01/2020
 */

@Service
public class ScheduleSummaryServiceimpl implements ScheduleSummaryService {

    ScheduleSummaryRepository scheduleSummaryRepository;

    @Autowired
    public ScheduleSummaryServiceimpl(ScheduleSummaryRepository scheduleSummaryRepository) {
        this.scheduleSummaryRepository = scheduleSummaryRepository;
    }

    @Override
    public ScheduleSummary getScheduleByDateAndEmployee(Date date, Employee employee) {
        return scheduleSummaryRepository.findByScheduleDateAndEmployee(date, employee);
    }

    @Override
    public List<ScheduleSummary> getScheduleByDate(Date date) {
        return scheduleSummaryRepository.findByScheduleDate(date);
    }

    @Override
    public ScheduleSummary create(Date date, Employee employee) {
        Optional<ScheduleSummary> scheduleSummary = Optional.ofNullable(getScheduleByDateAndEmployee(date, employee));
        ScheduleSummary summary = null;

        if(scheduleSummary.isPresent()) {
            throw new RuntimeException("Dla danego użytkownika i daty istnieje już grafik.");
        } else {
            summary = new ScheduleSummary(employee, date.toString());
            scheduleSummaryRepository.save(summary);
        }
        return summary;
    }

    @Override
    public void massCreate(Date date, List<Employee> employees) {
        List<ScheduleSummary> employeeList = new ArrayList<>();

        for (Employee employee: employees) {
            ScheduleSummary summary = new ScheduleSummary();
            summary.setEmployee(employee);
            summary.setScheduleDate(date);
            employeeList.add(summary);
        }
        scheduleSummaryRepository.saveAll(employeeList);
    }
}
