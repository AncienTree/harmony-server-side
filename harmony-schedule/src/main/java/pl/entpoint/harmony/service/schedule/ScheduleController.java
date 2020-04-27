package pl.entpoint.harmony.service.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.entpoint.harmony.entity.schedule.Schedule;

import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 03/02/2020
 */

@RestController
@RequestMapping("/api/schedule")
@CrossOrigin(origins = "http://localhost:4200")
public class ScheduleController {

    ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("listSchedule")
    public List<Schedule> scheduleList() {
        return scheduleService.getActiveSchedules();
    }

    @GetMapping("all")
    public List<Schedule> activeScheduleList() {
        return scheduleService.getSchedules();
    }
}
