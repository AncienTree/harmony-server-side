package pl.entpoint.harmony.service.schedule.record;

import java.time.LocalDate;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.pojo.Presence;
import pl.entpoint.harmony.entity.pojo.Record;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;
import pl.entpoint.harmony.service.employee.EmployeeService;

/**
 * @author Mateusz Dąbek
 * @created 20/11/2019
 */

@RestController
@RequestMapping("/api/schedule/record")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Api(tags = "Schedule Record Controller")
public class ScheduleRecordController {

    private final ScheduleRecordService scheduleRecordService;
    private final EmployeeService employeeService;

    @GetMapping("/{id}/{date}")
    @ApiOperation(value = "Get all schedule records by employee id and date.", nickname = "Get all schedule records by employee id and date.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Employee id", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "date", value = "Date in string", required = true, dataType = "String", paramType = "path")
    })
    public List<ScheduleRecord> getScheduleByDateAndEmployee(@PathVariable Long id, @PathVariable String date) {
        Employee employee = employeeService.getEmployeeDecrypted(id);

        return scheduleRecordService.getScheduleRecordByDateAndEmployee(LocalDate.parse(date), employee);
    }

    @GetMapping("/{date}")
    @ApiOperation(value = "Get all data to Presence table.", nickname = "Get all data to Presence table.")
    @ApiImplicitParam(name = "date", value = "Date in string", required = true, dataType = "String", paramType = "path")
    public List<Presence> getPresenceData(@PathVariable String date) {
        return scheduleRecordService.getScheduleRecordForPresence(LocalDate.parse(date));
    }

    @PostMapping("/")
    @ApiOperation(value = "Create new schedule record.", nickname = "Create new schedule record.")
    @ApiImplicitParam(name = "scheduleRecord", value = "Record body", required = true, dataType = "Record", paramType = "path")
    public ResponseEntity<String> createRecord(@RequestBody Record scheduleRecord) {
        scheduleRecordService.create(scheduleRecord);

        return new ResponseEntity<>("Utworzono rekord", HttpStatus.CREATED);
    }

    @PutMapping("/")
    @ApiOperation(value = "Update schedule record.", nickname = "Update schedule record.")
    @ApiImplicitParam(name = "scheduleRecord", value = "Record body", required = true, dataType = "Record", paramType = "path")
    public ResponseEntity<String> updateRecord(@RequestBody Record scheduleRecord) {
        scheduleRecordService.update(scheduleRecord);

        return new ResponseEntity<>("Zaktualizowane rekord", HttpStatus.OK);
    }
}
