package pl.entpoint.harmony.service.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.model.SimpleEmployee;
import pl.entpoint.harmony.entity.schedule.ScheduleSummary;
import pl.entpoint.harmony.service.employee.EmployeeService;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Mateusz Dąbek
 * @created 30/01/2020
 */

@RestController
@RequestMapping("/api/schedule")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class ScheduleSummaryController {

    private EmployeeService employeeService;
    private ScheduleSummaryService scheduleSummaryService;

    @Autowired
    public ScheduleSummaryController(EmployeeService employeeService, ScheduleSummaryService scheduleSummaryService) {
        this.employeeService = employeeService;
        this.scheduleSummaryService = scheduleSummaryService;
    }

    @GetMapping("/")
    ScheduleSummary getScheduleByDateAndEmployee(@RequestBody Map<String, String> schedule) {
        Optional<Employee> employee = Optional.ofNullable(employeeService.getEmployee(Long.valueOf(schedule.get("employee"))));
        SimpleEmployee simpleEmployee = new SimpleEmployee(employee.get());

        Optional<ScheduleSummary> optSummary = Optional.ofNullable(scheduleSummaryService.getScheduleByDateAndEmployee(
                Date.valueOf(schedule.get("date")),
                employee.get()));
        ScheduleSummary summary = optSummary.get();
        summary.setSimpleEmployee(simpleEmployee);

        return summary;
    }

    @GetMapping("/date/{date}")
    List<ScheduleSummary> getScheduleByDate(@PathVariable String date) {
        List<ScheduleSummary> summary = scheduleSummaryService.getScheduleByDate(Date.valueOf(date));

        // Przypisanie SimpleEmployee do każdego grafiku z listy
        for (ScheduleSummary scheduleSummary: summary) {
            scheduleSummary.setSimpleEmployee(new SimpleEmployee(scheduleSummary.getEmployee()));
        }
        return summary;
    }
}
