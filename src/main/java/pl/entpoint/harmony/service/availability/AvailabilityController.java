package pl.entpoint.harmony.service.availability;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.entpoint.harmony.entity.availability.Availability;
import pl.entpoint.harmony.entity.pojo.AvailabilityHelper;

import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 14.12.2020
 */

@RestController
@RequestMapping("/api/availability")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Api(tags = "Availability Controller")
public class AvailabilityController {

    final AvailabilityService availabilityService;

    @GetMapping("/{date}")
    @ApiOperation(value = "Get availability by date.", nickname = "availability by date.")
    @ApiImplicitParam(name = "date", value = "Date in string", required = true, dataType = "String", paramType = "path")
    public Availability getAvailability(@PathVariable String date) {
        return availabilityService.getAvailability(LocalDate.parse(date));
    }

    @PostMapping("/check")
    @ApiOperation(value = "Check if availability is created, if not return stats.", nickname = "Check if availability is created, if not return stats.")
    @ApiImplicitParam(name = "date", value = "Date in string", required = true, dataType = "String", paramType = "body")
    public AvailabilityHelper check(@RequestBody String date) {
        return availabilityService.checkAvailability(LocalDate.parse(date));
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create availability.", nickname = "Create availability.")
    @ApiImplicitParam(name = "creation", value = "Date in string", required = true, dataType = "AvailabilityCreation", paramType = "body")
    public ResponseEntity<String> create(@RequestBody String date) {
        availabilityService.create(LocalDate.parse(date));

        return new ResponseEntity<>("Utworzono nową dyspozycyjność.", HttpStatus.CREATED);
    }

    @PostMapping("/close")
    @ApiOperation(value = "Close active availability.", nickname = "Close active availability.")
    @ApiImplicitParam(name = "date", value = "Date in string", required = true, dataType = "String", paramType = "body")
    public ResponseEntity<String> checkAvailability(@RequestBody String date) {
        availabilityService.closeAvailability(LocalDate.parse(date));

        return new ResponseEntity<>("Zamknięto aktywną dyspozycyjność.", HttpStatus.CREATED);
    }
}
