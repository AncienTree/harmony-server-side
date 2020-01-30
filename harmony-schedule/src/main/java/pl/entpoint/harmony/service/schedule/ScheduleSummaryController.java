package pl.entpoint.harmony.service.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleSummary;
import pl.entpoint.harmony.service.employee.EmployeeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Mateusz Dąbek
 * @created 30/01/2020
 */

@RestController
@RequestMapping("/api")
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

    @GetMapping("/schedule")
    ScheduleSummary getScheduleByDateAndEmployee(@RequestBody Map<String, String> schedule) {
        Optional<Employee> employee = Optional.ofNullable(employeeService.getEmployee(Long.valueOf(schedule.get("employeeId"))));

        Optional<ScheduleSummary> summary = Optional.ofNullable(scheduleSummaryService.getScheduleByDateAndEmployee(
                (LocalDate.parse(schedule.get("date"))),
                employee.get()));

        return summary.get();
    }

    @GetMapping("/schedule/date")
    List<ScheduleSummary> getScheduleByDate(@RequestBody String date) {
        return scheduleSummaryService.getScheduleByDate(LocalDate.parse(date));
    }
}
