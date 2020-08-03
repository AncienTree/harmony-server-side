package pl.entpoint.harmony.service.schedule.summary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.dto.EmployeeScheduleList;
import pl.entpoint.harmony.entity.dto.SimpleEmployee;
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
@RequestMapping("/api/schedule")
@CrossOrigin(origins = "http://localhost:4200")
public class ScheduleSummaryController {

    private final EmployeeService employeeService;
    private final ScheduleSummaryService scheduleSummaryService;

    @Autowired
    public ScheduleSummaryController(EmployeeService employeeService, ScheduleSummaryService scheduleSummaryService) {
        this.employeeService = employeeService;
        this.scheduleSummaryService = scheduleSummaryService;
    }   

    @GetMapping("/date/{date}")
    public List<ScheduleSummary> getScheduleByDate(@PathVariable String date) {
        List<ScheduleSummary> summary = scheduleSummaryService.getScheduleByDate(LocalDate.parse(date));

        // Przypisanie SimpleEmployee do każdego grafiku z listy
        for (ScheduleSummary scheduleSummary: summary) {
            scheduleSummary.setSimpleEmployee(new SimpleEmployee(scheduleSummary.getEmployee()));
        }
        return summary;
    }

    @GetMapping("/{date}/{status}")
    public List<ScheduleSummary> getScheduleByDate(@PathVariable String date, @PathVariable String status) {
        List<ScheduleSummary> summary = scheduleSummaryService.getScheduleByDate(LocalDate.parse(date));

        // Filtorwanie rekordów po danym statusie
        summary.forEach(p -> p.getScheduleRecords().removeIf(g -> g.getTypes().checkValue(status)));

        // Przypisanie SimpleEmployee do każdego grafiku z listy
        for (ScheduleSummary scheduleSummary: summary) {
            scheduleSummary.setSimpleEmployee(new SimpleEmployee(scheduleSummary.getEmployee()));
        }
        return summary;
    }
    
    @GetMapping("/employee/{date}")
    public List<Employee> getEmployeeWithoutSchedule(@PathVariable String date) {
    	LocalDate localDate = LocalDate.parse(date);
    	
        return scheduleSummaryService.getEmployeeWithoutSchedule(localDate);
    }
    
    @GetMapping("/employeeList/{date}")
    public EmployeeScheduleList getListOfEmployee(@PathVariable String date) {
    	LocalDate localDate = LocalDate.parse(date);
    	
        return scheduleSummaryService.getListOfEmployees(localDate);
    }
    
    @PostMapping("/add")
    public ResponseEntity<String> createSummary(@RequestBody Map<String, String> body) {
    	Employee employee = employeeService.getEmployee(Long.valueOf(body.get("id")));
       Optional<ScheduleSummary> optSummary = Optional.ofNullable(scheduleSummaryService.getScheduleByDateAndEmployee(
    		   LocalDate.parse(body.get("date")), employee));

        if(optSummary.isPresent()) {
            return new ResponseEntity<>("Dla danego użytkownika i daty istnieje już grafik.", HttpStatus.BAD_REQUEST);
        } else {
        	scheduleSummaryService.create(LocalDate.parse(body.get("date")), employee);
            return new ResponseEntity<>("Utworzono grafik dla " + employee.getFirstName() + " " + employee.getLastName(),
                    HttpStatus.OK);
       }
    }    

    @PostMapping("/employee/{date}")
    public ResponseEntity<String> addUsersToSchedule(@RequestBody Long[] id, @PathVariable String date){
    	LocalDate localDate = LocalDate.parse(date);
        for (Long ids : id) {
            Employee employee = employeeService.getEmployeeNotDecrypted(ids);
            scheduleSummaryService.create(localDate, employee);
        }
        return new ResponseEntity<>("Utworzono grafik dla " + id.length + " pracowników",
                HttpStatus.CREATED);
    }
}

















