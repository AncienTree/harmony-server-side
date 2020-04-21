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

    @GetMapping("/date/{date}")
    List<ScheduleSummary> getScheduleByDate(@PathVariable String date) {
        List<ScheduleSummary> summary = scheduleSummaryService.getScheduleByDate(Date.valueOf(date));

        // Przypisanie SimpleEmployee do każdego grafiku z listy
        for (ScheduleSummary scheduleSummary: summary) {
            scheduleSummary.setSimpleEmployee(new SimpleEmployee(scheduleSummary.getEmployee()));
        }
        return summary;
    }

    @GetMapping("/{date}/{status}")
    List<ScheduleSummary> getScheduleByDate(@PathVariable String date, @PathVariable String status) {
        List<ScheduleSummary> summary = scheduleSummaryService.getScheduleByDate(Date.valueOf(date));

        // Filtorwanie rekordów po danym statusie
        summary.forEach(p -> p.getScheduleRecords().removeIf(g -> g.getTypes().checkValue(status)));

        // Przypisanie SimpleEmployee do każdego grafiku z listy
        for (ScheduleSummary scheduleSummary: summary) {
            scheduleSummary.setSimpleEmployee(new SimpleEmployee(scheduleSummary.getEmployee()));
        }
        return summary;
    }
    
    @PostMapping("/add")
    void createSummary(@RequestBody Map<String, String> body) {
    	Employee employee = employeeService.getEmployee(Long.valueOf(body.get("id")));
        Optional<ScheduleSummary> optSummary;
        ScheduleSummary summary;
        if (employee != null){
            optSummary = Optional.ofNullable(scheduleSummaryService.getScheduleByDateAndEmployee(
                    Date.valueOf(body.get("date")), employee));
        } else {
            throw new RuntimeException("Użytkownik nie istnieje");
        }
        if(optSummary.isPresent()) {
        	throw new RuntimeException("Grafik istnieje dla użytkownika o danej dacie");
        } else {
        	summary = new ScheduleSummary(employee, body.get("date"));
//        	summary.setId(0L);
        	scheduleSummaryService.create(summary);
        }
    }
}

















