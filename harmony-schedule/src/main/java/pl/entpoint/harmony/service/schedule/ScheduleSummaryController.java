package pl.entpoint.harmony.service.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<String> createSummary(@RequestBody Map<String, String> body) {
    	Employee employee = employeeService.getEmployee(Long.valueOf(body.get("id")));
        Optional<ScheduleSummary> optSummary = Optional.ofNullable(scheduleSummaryService.getScheduleByDateAndEmployee(
                Date.valueOf(body.get("date")), employee));;
        ScheduleSummary summary;

        if(optSummary.isPresent()) {
            return new ResponseEntity<>("Dla danego użytkownika i daty istnieje już grafik.", HttpStatus.BAD_REQUEST);
        } else {
        	summary = new ScheduleSummary(employee, body.get("date"));
        	scheduleSummaryService.create(summary);

            return new ResponseEntity<>("Utworzono grafik dla " + employee.getFirstName() + " " + employee.getLastName(),
                    HttpStatus.CREATED);
        }
    }
}

















