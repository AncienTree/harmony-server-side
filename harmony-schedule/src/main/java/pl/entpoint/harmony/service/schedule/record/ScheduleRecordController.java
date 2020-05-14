package pl.entpoint.harmony.service.schedule.record;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;
import pl.entpoint.harmony.service.employee.EmployeeService;

/**
 * @author Mateusz Dąbek
 * @created 20/11/2019
 */

@RestController
@RequestMapping("/api/schedule/record")
@CrossOrigin(origins = "http://localhost:4200")
public class ScheduleRecordController {

    private ScheduleRecordService scheduleRecordService;
    private EmployeeService employeeService;

    @Autowired
    public ScheduleRecordController(ScheduleRecordService scheduleRecordService, EmployeeService employeeService) {
        this.scheduleRecordService = scheduleRecordService;
        this.employeeService = employeeService;
    }

    @GetMapping("{id}/{date}")
    public List<ScheduleRecord> getScheduleByDateAndEmployee(@PathVariable String id, @PathVariable String date) {
        Employee theEmpl = employeeService.getEmployee(Long.valueOf(id));

        return scheduleRecordService.getScheduleRecodByDateAndEmployee(Date.valueOf(date), theEmpl);
    }
}
