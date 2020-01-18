package pl.entpoint.harmony.service.schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pl.entpoint.harmony.models.employee.Employee;
import pl.entpoint.harmony.models.schedule.ScheduleRecord;
import pl.entpoint.harmony.models.schedule.enums.ScheduleStatus;
import pl.entpoint.harmony.service.employee.EmployeeService;

/**
 * @author Mateusz Dąbek
 * @created 20/11/2019
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class ScheduleController {

    ScheduleService scheduleService;
    EmployeeService employeeService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, EmployeeService employeeService) {
        this.scheduleService = scheduleService;
        this.employeeService = employeeService;
    }

    @GetMapping("/byStatus/{status}")
    public List<ScheduleRecord> getScheduleByStatus(@PathVariable String status) {
        return scheduleService.getScheduleByStatus(ScheduleStatus.valueOf(status));
    }

    @GetMapping("/byEmployee/{id}")
    public List<ScheduleRecord> getScheduleByEmployee(@PathVariable int id) {
        Employee theEmpl = employeeService.getEmployee(id);

        return scheduleService.getScheduleByEmployee(theEmpl);
    }

    @GetMapping("/byDate/{date}")
    public List<ScheduleRecord> getScheduleByDate(@PathVariable String date) {
        System.out.println(date);
        System.out.println(LocalDate.parse(date));
        return scheduleService.getScheduleByDate(LocalDate.parse(date));
    }

    @GetMapping("/byDates")
    public List<ScheduleRecord> getScheduleBetweenDate(@RequestBody Map<String, LocalDate> dates) {

        return scheduleService.getScheduleBetweenDate(dates.get("start"), dates.get("end"));
    }

    @GetMapping("/byDateAndEmployee")
    public List<ScheduleRecord> getScheduleByDateAndEmployee(@RequestBody Map<String, Object> dateAndId) {
        Employee theEmpl = employeeService.getEmployee((int) dateAndId.get("id"));

        return scheduleService.getScheduleByDateAndEmployee(LocalDate.parse((String) dateAndId.get("date")), theEmpl);
    }

    @GetMapping("/byDatesAndEmployee")
    public List<ScheduleRecord> getScheduleBetweenDateAndEmployee(@RequestBody Map<String, Object> datesAndId) {
        Employee theEmpl = employeeService.getEmployee((int) datesAndId.get("id"));

        return scheduleService.getScheduleBetweenDateAndEmployee((LocalDate) datesAndId.get("star"),
                (LocalDate) datesAndId.get("end"), theEmpl);
    }

    @GetMapping("/byDatesAndStatus")
    public List<ScheduleRecord> getScheduleBetweenDateAndStatus(@RequestBody Map<String, Object> datesAndStatus) {
        return scheduleService.getScheduleBetweenDateAndStatus(LocalDate.parse((String) datesAndStatus.get("start")),
                LocalDate.parse((String) datesAndStatus.get("end")), ScheduleStatus.valueOf((String) datesAndStatus.get("status")));
    }
}
