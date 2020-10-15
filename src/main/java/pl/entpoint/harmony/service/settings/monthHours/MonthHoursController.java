package pl.entpoint.harmony.service.settings.monthHours;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.entpoint.harmony.entity.settings.MonthHours;

/**
 * @author Mateusz Dąbek
 * @created 20.05.2020
 */

@RestController
@RequestMapping("/api/setting/monthhours")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class MonthHoursController {
    private final MonthHoursService monthHoursService;

    @GetMapping("/")
    public List<MonthHours> getYear() {
        return monthHoursService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody MonthHours monthHours) {
        monthHoursService.create(monthHours);
        
        return new ResponseEntity<>("Utworzono nowe roboczogodziny miesiąca", HttpStatus.CREATED);
    }
    
    @PatchMapping("/")
    public ResponseEntity<String> update(@RequestBody Map<String, String> monthHours) {
        monthHoursService.change(monthHours);
        
        return new ResponseEntity<>("Zaktualizowano dane", HttpStatus.OK);
    }
    
    @DeleteMapping("/")
    public ResponseEntity<String> delete(@RequestBody MonthHours monthHours) {
        monthHoursService.delete(monthHours);
        
        return new ResponseEntity<>("Usunięto roboczogodziny za rok " + monthHours.getYear(), HttpStatus.OK);
    }
}
