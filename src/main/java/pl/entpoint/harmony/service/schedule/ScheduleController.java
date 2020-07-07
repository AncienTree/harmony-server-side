package pl.entpoint.harmony.service.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;
import pl.entpoint.harmony.entity.schedule.Schedule;
import pl.entpoint.harmony.service.employee.EmployeeService;
import pl.entpoint.harmony.service.schedule.summary.ScheduleSummaryService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author Mateusz Dąbek
 * @created 03/02/2020
 */

@RestController
@RequestMapping("/api/schedule")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class ScheduleController {

	final ScheduleService scheduleService;
	final ScheduleSummaryService scheduleSummaryService;
	final EmployeeService employeeService;

	@Autowired
	public ScheduleController(ScheduleService scheduleService, ScheduleSummaryService scheduleSummaryService,
							  EmployeeService employeeService) {
		this.scheduleService = scheduleService;
		this.scheduleSummaryService = scheduleSummaryService;
		this.employeeService = employeeService;
	}

	@GetMapping("listSchedule")
	public List<Schedule> activeScheduleList() {
		return scheduleService.getActiveSchedules();
	}

	@GetMapping("all")
	public List<Schedule> ScheduleList() {
		return scheduleService.getSchedules();
	}

	@PatchMapping("changeStatus")
	public ResponseEntity<String> changestatus(@RequestBody Map<String, String> body) {
		log.info("Zmiana statusu grafiku");
		log.debug(body.toString());
		boolean active = Boolean.parseBoolean(body.get("active"));
		boolean visible = Boolean.parseBoolean(body.get("visible"));

		if (active && !visible) {
			log.warn("Grafik nie może być ustawiony jako niewidoczny i jednocześnie aktywny.");
			return new ResponseEntity<>("Grafik nie może być ustawiony jako niewidoczny i jednocześnie aktywny.", HttpStatus.BAD_REQUEST);
		}

		log.info("Wysłanie do serwisu zmiane grafiku");
		scheduleService.changeStatus(Long.parseLong(body.get("id")), active, visible);
		return new ResponseEntity<>("Zmieniono statusy dla grafiku o id " + body.get("id"), HttpStatus.OK);
	}

	@PostMapping("create")
	public ResponseEntity<String> createSchedule(@RequestBody String date) {
		scheduleService.createSchedule(LocalDate.parse(date));
		List<Employee> employees = employeeService.getEmployeesByStatus(WorkStatus.WORK);

		scheduleSummaryService.massCreate(LocalDate.parse(date), employees);

		return new ResponseEntity<>("Utworzono nowy harmonogram dla daty " + date, HttpStatus.CREATED);
	}
}
