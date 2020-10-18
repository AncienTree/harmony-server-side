package pl.entpoint.harmony.service.settings.monthHours;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.entpoint.harmony.entity.pojo.controller.MonthlyHoursPojo;
import pl.entpoint.harmony.entity.settings.MonthHours;

/**
 * @author Mateusz Dąbek
 * @created 20.05.2020
 */

@RestController
@RequestMapping("/api/setting/monthhours")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Api(tags = "Monthly Hours Controller")
public class MonthHoursController {
    private final MonthHoursService monthHoursService;

    @GetMapping("/")
    @ApiOperation(value = "Get all monthly hours.", nickname = "Get all monthly hours.")
    public List<MonthHours> getYear() {
        return monthHoursService.getAll();
    }

    @PostMapping("/")
    @ApiOperation(value = "Create new monthly hours.", nickname = "Create new monthly hours.")
    @ApiImplicitParam(name = "monthHours", value = "Month Hours body", required = true, dataType = "MonthlyHoursPojo", paramType = "body")
    public ResponseEntity<String> create(@RequestBody MonthlyHoursPojo monthHours) {
        MonthHours hours = new MonthHours(monthHours);
        monthHoursService.create(hours);
        
        return new ResponseEntity<>("Utworzono nowe roboczogodziny miesiąca", HttpStatus.CREATED);
    }
    
    @PatchMapping("/")
    @ApiOperation(value = "Update monthly hours.", nickname = "Update monthly hours.")
    @ApiImplicitParam(name = "monthHours", value = "Month Hours body", required = true, dataType = "MonthlyHoursPojo", paramType = "body")
    public ResponseEntity<String> update(@RequestBody MonthlyHoursPojo monthHours) {
        monthHoursService.change(monthHours);
        
        return new ResponseEntity<>("Zaktualizowano dane", HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete monthly hours.", nickname = "Delete monthly hours.")
    @ApiImplicitParam(name = "id", value = "Month Hours id", required = true, dataType = "int", paramType = "path")
    public ResponseEntity<String> delete(@PathVariable int id) {
        monthHoursService.delete(id);
        
        return new ResponseEntity<>("Usunięto roboczogodziny.", HttpStatus.OK);
    }
}
