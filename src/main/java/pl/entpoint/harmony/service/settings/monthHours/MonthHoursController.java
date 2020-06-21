package pl.entpoint.harmony.service.settings.monthHours;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.entpoint.harmony.entity.settings.MonthHours;

import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 20.05.2020
 */

@RestController
@RequestMapping("/api/setting/monthhours")
@CrossOrigin(origins = "http://localhost:4200")
public class MonthHoursController {
    MonthHoursService monthHoursService;

    @Autowired
    public MonthHoursController(MonthHoursService monthHoursService) {
        this.monthHoursService = monthHoursService;
    }

    @GetMapping("/{year}")
    public List<MonthHours> getAll(@PathVariable int year) {
        return monthHoursService.getMonthHoursOnYear(year);
    }

    @PostMapping("/")
    ResponseEntity<String> create(@RequestBody MonthHours monthHours) {

        monthHoursService.create(monthHours);
        return new ResponseEntity<>("Utworzono nowe roboczogodziny miesiąca", HttpStatus.CREATED);
    }
}
