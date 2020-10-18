package pl.entpoint.harmony.service.schedule;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;
import pl.entpoint.harmony.entity.pojo.SchedulePojo;
import pl.entpoint.harmony.entity.schedule.Schedule;
import pl.entpoint.harmony.service.employee.EmployeeService;
import pl.entpoint.harmony.service.schedule.summary.ScheduleSummaryService;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 03/02/2020
 */

@RestController
@RequestMapping("/api/schedule")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j @AllArgsConstructor
@Api(tags = "Schedule Controller")
public class ScheduleController {

	final ScheduleService scheduleService;
	final ScheduleSummaryService scheduleSummaryService;
	final EmployeeService employeeService;

	@GetMapping("/")
	@ApiOperation(value = "Get all active schedules.", nickname = "Get all active schedules.")
	public List<Schedule> activeScheduleList() {
		return scheduleService.getActiveSchedules();
	}
	
	@GetMapping("/my")
	@ApiOperation(value = "Get all my schedules.", nickname = "Get all my schedules.")
	public List<Schedule> MyScheduleList(Principal principal) {
		return scheduleService.getMySchedules(principal.getName());
	}

	@GetMapping("/all")
	@ApiOperation(value = "Get all schedules.", nickname = "Get all schedules.")
	public List<Schedule> ScheduleLists() {
		return scheduleService.getSchedules();
	}

	@PatchMapping("/")
	@ApiOperation(value = "Update schedule.", nickname = "Update schedule.")
	@ApiImplicitParam(name = "schedule", value = "Schedule body", required = true, dataType = "SchedulePojo", paramType = "body")
	public ResponseEntity<String> changestatus(@RequestBody SchedulePojo schedule) {
		log.info("Zmiana statusu grafiku");
		log.debug(schedule.toString());
		if (schedule.isActive() && !schedule.isVisible()) {
			log.warn("Grafik nie może być ustawiony jako niewidoczny i jednocześnie aktywny.");
			return new ResponseEntity<>("Grafik nie może być ustawiony jako niewidoczny i jednocześnie aktywny.", HttpStatus.BAD_REQUEST);
		}

		log.info("Wysłanie do serwisu zmiane grafiku");
		scheduleService.changeStatus(schedule.getId(), schedule.isActive(), schedule.isVisible());
		return new ResponseEntity<>("Zmiana grafiku została zapisana", HttpStatus.OK);
	}

	@PostMapping("/")
	@ApiOperation(value = "Create new schedule.", nickname = "Create new schedule.")
	@ApiImplicitParam(name = "date", value = "Date in string", required = true, dataType = "String", paramType = "body")
	public ResponseEntity<String> createSchedule(@RequestBody String date) {
		scheduleService.createSchedule(LocalDate.parse(date));
		List<Employee> employees = employeeService.getEmployeesByStatus(WorkStatus.WORK);

		scheduleSummaryService.massCreate(LocalDate.parse(date), employees);

		return new ResponseEntity<>("Utworzono nowy harmonogram dla daty " + date, HttpStatus.CREATED);
	}
}
