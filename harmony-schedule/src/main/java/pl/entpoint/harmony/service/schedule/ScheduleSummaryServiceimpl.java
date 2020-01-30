package pl.entpoint.harmony.service.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleSummary;

import java.time.LocalDate;
import java.util.List;

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
    public ScheduleSummary getScheduleByDateAndEmployee(LocalDate date, Employee employee) {
        return scheduleSummaryRepository.findByScheduleDateAndEmployee(date, employee);
    }

    @Override
    public List<ScheduleSummary> getScheduleByDate(LocalDate date) {
        return scheduleSummaryRepository.findByScheduleDate(date);
    }
}
