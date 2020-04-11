package pl.entpoint.harmony.service.schedule;

import java.sql.Date;
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
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;
import pl.entpoint.harmony.entity.schedule.enums.ScheduleStatus;
import pl.entpoint.harmony.service.employee.EmployeeService;

/**
 * @author Mateusz Dąbek
 * @created 20/11/2019
 */

@RestController
@RequestMapping("/api/schedule/record")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class ScheduleRecordController {

    private ScheduleRecordService scheduleRecordService;
    private EmployeeService employeeService;

    @Autowired
    public ScheduleRecordController(ScheduleRecordService scheduleRecordService, EmployeeService employeeService) {
        this.scheduleRecordService = scheduleRecordService;
        this.employeeService = employeeService;
    }

    @GetMapping("/byStatus/{status}")
    public List<ScheduleRecord> getScheduleByStatus(@PathVariable String status) {
        return scheduleRecordService.getScheduleByStatus(ScheduleStatus.valueOf(status));
    }

    @GetMapping("/byEmployee/{id}")
    public List<ScheduleRecord> getScheduleByEmployee(@PathVariable Long id) {
        Employee theEmpl = employeeService.getEmployee(id);

        return scheduleRecordService.getScheduleByEmployee(theEmpl);
    }

    @GetMapping("/byDate/{date}")
    public List<ScheduleRecord> getScheduleByDate(@PathVariable String date) {
        return scheduleRecordService.getScheduleByDate(Date.valueOf(date));
    }

    @GetMapping("/byDates")
    public List<ScheduleRecord> getScheduleBetweenDate(@RequestBody Map<String, String> dates) {

        return scheduleRecordService.getScheduleBetweenDate(Date.valueOf("start"), Date.valueOf("end"));
    }

    @GetMapping("{id}/{date}")
    public List<ScheduleRecord> getScheduleByDateAndEmployee(@PathVariable String id, @PathVariable String date) {
        Employee theEmpl = employeeService.getEmployee(Long.valueOf(id));

        return scheduleRecordService.getScheduleByDateAndEmployee(Date.valueOf(date), theEmpl);
    }

    @GetMapping("/byDatesAndEmployee")
    public List<ScheduleRecord> getScheduleBetweenDateAndEmployee(@RequestBody Map<String, Object> datesAndId) {
        Employee theEmpl = employeeService.getEmployee((long)datesAndId.get("id"));

        return scheduleRecordService.getScheduleBetweenDateAndEmployee(Date.valueOf((String) datesAndId.get("star")),
                Date.valueOf((String) datesAndId.get("end")), theEmpl);
    }

    @GetMapping("/byDatesAndStatus")
    public List<ScheduleRecord> getScheduleBetweenDateAndStatus(@RequestBody Map<String, Object> datesAndStatus) {
        return scheduleRecordService.getScheduleBetweenDateAndStatus(Date.valueOf((String) datesAndStatus.get("start")),
                Date.valueOf((String) datesAndStatus.get("end")), ScheduleStatus.valueOf((String) datesAndStatus.get("status")));
    }
}
