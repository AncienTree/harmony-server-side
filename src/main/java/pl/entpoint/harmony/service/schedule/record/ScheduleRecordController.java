package pl.entpoint.harmony.service.schedule.record;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.entpoint.harmony.entity.dto.Presence;
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

    private final ScheduleRecordService scheduleRecordService;
    private final EmployeeService employeeService;

    @Autowired
    public ScheduleRecordController(ScheduleRecordService scheduleRecordService, EmployeeService employeeService) {
        this.scheduleRecordService = scheduleRecordService;
        this.employeeService = employeeService;
    }

    @GetMapping("{id}/{date}")
    public List<ScheduleRecord> getScheduleByDateAndEmployee(@PathVariable String id, @PathVariable String date) {
        Employee theEmpl = employeeService.getEmployee(Long.valueOf(id));

        return scheduleRecordService.getScheduleRecordByDateAndEmployee(LocalDate.parse(date), theEmpl);
    }

    @GetMapping("{date}")
    public List<Presence> getPresenceData(@PathVariable String date) {
        return scheduleRecordService.getScheduleRecordForPresence(LocalDate.parse(date));
    }

    @PostMapping("")
    public ResponseEntity<String> createRecord(@RequestBody ScheduleRecord scheduleRecord) {
        System.out.println("---------------------------------------------");
        System.out.println(scheduleRecord);
        System.out.println("---------------------------------------------");
     //   scheduleRecordService.create(scheduleRecord);

        return new ResponseEntity<>("Utworzono rekord", HttpStatus.CREATED);
    }

    @PatchMapping("")
    public ResponseEntity<String> updateRecord(@RequestBody ScheduleRecord scheduleRecord) {
        scheduleRecordService.update(scheduleRecord);

        return new ResponseEntity<>("Zaktualizowane rekord", HttpStatus.OK);
    }
}
