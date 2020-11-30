package pl.entpoint.harmony.service.schedule.summary;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.pojo.EmployeeScheduleList;
import pl.entpoint.harmony.entity.pojo.SimpleEmployee;
import pl.entpoint.harmony.entity.pojo.SimpleSchedule;
import pl.entpoint.harmony.entity.schedule.ScheduleSummary;
import pl.entpoint.harmony.service.employee.EmployeeService;
import pl.entpoint.harmony.util.ScheduleSummaryConventer;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 30/01/2020
 */

@RestController
@RequestMapping("/api/schedule")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Api(tags = "Schedule Summary Controller")
public class ScheduleSummaryController {

    private final EmployeeService employeeService;
    private final ScheduleSummaryService scheduleSummaryService;

    @GetMapping("/date/{date}")
    @ApiOperation(value = "Get schedules summary by date.", nickname = "Get schedules summary by date.")
    @ApiImplicitParam(name = "date", value = "Date in string", required = true, dataType = "String", paramType = "path")
    public List<ScheduleSummary> getScheduleByDate(@PathVariable String date) {
        List<ScheduleSummary> summary = scheduleSummaryService.getScheduleByDate(LocalDate.parse(date));

        // Przypisanie SimpleEmployee do każdego grafiku z listy
        for (ScheduleSummary scheduleSummary: summary) {
            scheduleSummary.setSimpleEmployee(new SimpleEmployee(scheduleSummary.getEmployee()));
        }
        return summary;
    }

    @GetMapping("/date/v2/{date}")
    @ApiOperation(value = "Get schedules summary by date.", nickname = "Get schedules summary by date.")
    @ApiImplicitParam(name = "date", value = "Date in string", required = true, dataType = "String", paramType = "path")
    public List<SimpleSchedule> getScheduleByDateV2(@PathVariable String date) {
        List<ScheduleSummary> summary = scheduleSummaryService.getScheduleByDate(LocalDate.parse(date));

        // Przypisanie SimpleEmployee do każdego grafiku z listy
        for (ScheduleSummary scheduleSummary: summary) {
            scheduleSummary.setSimpleEmployee(new SimpleEmployee(scheduleSummary.getEmployee()));
        }
        return ScheduleSummaryConventer.convert(summary);
    }
    
    @GetMapping("/date/{date}/my")
    @ApiOperation(value = "Get my schedule summary by date.", nickname = "Get my schedule summary by date.")
    @ApiImplicitParam(name = "date", value = "Date in string", required = true, dataType = "String", paramType = "path")
    public ScheduleSummary getMySchedule(@PathVariable String date, Principal principal) {
    	LocalDate localDate = LocalDate.parse(date);
    	
        return scheduleSummaryService.getMySchedule(localDate, principal.getName());
    }

    @GetMapping("/{date}/{status}")
    @ApiOperation(value = "Get schedules summary by date and status.", nickname = "Get schedules summary by date and status.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "Date in string", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "status", value = "Status in string", required = true, dataType = "ScheduleStatus", paramType = "path")
    })
    public List<ScheduleSummary> getScheduleByDateAndStatus(@PathVariable String date, @PathVariable String status) {
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
    @ApiOperation(value = "Get list of employee without schedule by date.", nickname = "Get list of employee without schedule by date.")
    @ApiImplicitParam(name = "date", value = "Date in string", required = true, dataType = "String", paramType = "path")
    public List<Employee> getEmployeeWithoutSchedule(@PathVariable String date) {
    	LocalDate localDate = LocalDate.parse(date);
    	
        return scheduleSummaryService.getEmployeeWithoutSchedule(localDate);
    }
    
    @GetMapping("/employee-list/{date}")
    @ApiOperation(value = "Get schedules summary by date without records.", nickname = "Get schedules summary by date without records.")
    @ApiImplicitParam(name = "date", value = "Date in string", required = true, dataType = "String", paramType = "path")
    public EmployeeScheduleList getListOfEmployee(@PathVariable String date) {
    	LocalDate localDate = LocalDate.parse(date);
    	
        return scheduleSummaryService.getListOfEmployees(localDate);
    }

    @PostMapping("/employee/{date}")
    @ApiOperation(value = "Create new schedule summary by list of employees id.", nickname = "Create new schedule summary by list of employees id.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "List of employees id", required = true, dataType = "long", paramType = "body"),
            @ApiImplicitParam(name = "date", value = "Date in string", required = true, dataType = "String", paramType = "path")
    })
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

















