package pl.entpoint.harmony.service.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pl.entpoint.harmony.entity.schedule.Schedule;

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

	ScheduleService scheduleService;

	@Autowired
	public ScheduleController(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
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
		Boolean active = Boolean.parseBoolean(body.get("active"));
		Boolean visible = Boolean.parseBoolean(body.get("visible"));
		
    	if(active == true && visible == false) {
    		log.warn("Grafik nie może być ustawiony jako niewidoczny i jednocześnie aktywny.");
    		return new ResponseEntity<>("Grafik nie może być ustawiony jako niewidoczny i jednocześnie aktywny.", HttpStatus.BAD_REQUEST);
    	}
    	
    	log.info("Wysłanie do serwisu zmiane grafiku");
    	scheduleService.changeStatus(Long.parseLong(body.get("id")), active, visible);
    	return new ResponseEntity<>("Zmieniono statusy dla grafiku o id " + body.get("id"), HttpStatus.OK);
    }
}
