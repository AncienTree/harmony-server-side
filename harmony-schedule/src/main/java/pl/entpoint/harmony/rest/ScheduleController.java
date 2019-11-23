package pl.entpoint.harmony.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pl.entpoint.harmony.entity.Employee;
import pl.entpoint.harmony.entity.Schedule;
import pl.entpoint.harmony.entity.enums.ScheduleStatus;
import pl.entpoint.harmony.service.EmployeeService;
import pl.entpoint.harmony.service.ScheduleService;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Slf4j
@Api(name = "Schedule API", description = "Endpoint do pobierania czasu pracy", stage = ApiStage.ALPHA)
public class ScheduleController {

	//TODO Utworzenie metod dla pobrania na danym typie, method POST, PUT, DELETE, PATCHE
	
	@Autowired
	ScheduleService scheduleService;
	@Autowired
	EmployeeService emplService;

	@GetMapping("/byStatus/{status}")
	@ApiMethod(description = "Pobiera liste harmonogramu na danym statusie")
	public List<Schedule> getScheduleByStatus(@ApiPathParam(name = "Schedule Status") @PathVariable String status) {
		return scheduleService.getScheduleByStatus(ScheduleStatus.valueOf(status));
	};

	@GetMapping("/byEmployee/{id}")
	@ApiMethod(description = "Pobiera liste harmonogramu dla danego pracownika")
	public List<Schedule> getScheduleByEmployee(@ApiPathParam(name = "Employee id") @PathVariable int id) {
		Employee theEmpl = emplService.getEmployee(id);

		return scheduleService.getScheduleByEmployee(theEmpl);
	};

	@GetMapping("/byDate/{date}")
	@ApiMethod(description = "Pobiera liste harmonogramu za dany dzień")
	public List<Schedule> getScheduleByDate(@ApiPathParam(name = "Data") @PathVariable String date) {
		System.out.println(date);
		System.out.println(LocalDate.parse(date));
		return scheduleService.getScheduleByDate(LocalDate.parse(date));
	};

	@GetMapping("/byDates")
	@ApiMethod(description = "Pobiera liste harmonogramu za dany okres czasu")
	public List<Schedule> getScheduleBetweenDate(@RequestBody Map<String, LocalDate> dates) {

		return scheduleService.getScheduleBetweenDate(dates.get("start"), dates.get("end"));
	};
	
	@GetMapping("/byDateAndEmployee")
	@ApiMethod(description = "Pobiera liste harmonogramu za dany dzień dla danego pracownika")
	public List<Schedule> getScheduleByDateAndEmployee(@RequestBody Map<String, Object> dateAndId) {
		Employee theEmpl = emplService.getEmployee((int) dateAndId.get("id"));

		return scheduleService.getScheduleByDateAndEmployee(LocalDate.parse((String)dateAndId.get("date")), theEmpl);
	};

	@GetMapping("/byDatesAndEmployee")
	@ApiMethod(description = "Pobiera liste harmonogramu za dany okres czasu dla danego pracownika")
	public List<Schedule> getScheduleBetweenDateAndEmployee(@RequestBody Map<String, Object> datesAndId) {
		Employee theEmpl = emplService.getEmployee((int) datesAndId.get("id"));

		return scheduleService.getScheduleBetweenDateAndEmployee((LocalDate) datesAndId.get("star"),
				(LocalDate) datesAndId.get("end"), theEmpl);
	};

	@GetMapping("/byDatesAndStatus")
	@ApiMethod(description = "Pobiera liste harmonogramu za dany okres czasu na danym statusie")
	public List<Schedule> getScheduleBetweenDateAndStatus(@RequestBody Map<String, Object> datesAndStatus) {
		return scheduleService.getScheduleBetweenDateAndStatus(LocalDate.parse((String)datesAndStatus.get("start")),
				LocalDate.parse((String)datesAndStatus.get("end")), ScheduleStatus.valueOf((String) datesAndStatus.get("status")));
	};
}