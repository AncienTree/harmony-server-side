package pl.entpoint.harmony.service.settings.dayOff;

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

import pl.entpoint.harmony.entity.pojo.controller.DayOffPojo;
import pl.entpoint.harmony.entity.settings.DayOff;

/**
 * @author Mateusz Dąbek
 * @created 19 maj 2020
 * 
 */

@RestController
@RequestMapping("/api/setting/dayoff")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Api(tags = "Day Off Controller")
public class DayOffController {
	private final DayOffService dayOffService;

	@GetMapping("/{year}")
	@ApiOperation(value = "Get day off by year.", nickname = "Get day off by year.")
	@ApiImplicitParam(name = "year", value = "Year in string", required = true, dataType = "String", paramType = "path")
	public List<DayOff> getDayOffByYear(@PathVariable String year){
		return dayOffService.getDayOffByYear(year);
	}
	
	@GetMapping("/between")
	@ApiOperation(value = "Get day off between two date.", nickname = "Get day off between two date.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "start", value = "Starting date", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "end", value = "Ending date", required = true, dataType = "String", paramType = "query")
	})
	public List<DayOff> getDayOffBetween(@RequestParam String start, @RequestParam String end){
		LocalDate st = LocalDate.parse(start);
		LocalDate ed = LocalDate.parse(end);
		System.out.println(st);

		return dayOffService.getDayOffBetweenDats(st, ed);
	}
	
	@PostMapping("/")
	@ApiOperation(value = "Create new day off.", nickname = "Create new day off.")
	@ApiImplicitParam(name = "day", value = "Day off body", required = true, dataType = "DayOffPojo", paramType = "body")
    public ResponseEntity<String> saveChange(@RequestBody DayOffPojo day){
		DayOff dayOff = new DayOff(day);

		dayOffService.create(dayOff);
        return new ResponseEntity<>("Dodano nowy dzień wolny.", HttpStatus.CREATED);
    }
	
	@PatchMapping("/")
	@ApiOperation(value = "Update day off.", nickname = "Update day off.")
	@ApiImplicitParam(name = "day", value = "Day off body", required = true, dataType = "DayOffPojo", paramType = "body")
    public ResponseEntity<String> update(@RequestBody DayOffPojo day){
		dayOffService.update(day);
		
        return new ResponseEntity<>("Zaktualizowano opis.", HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete day off.", nickname = "Delete day off.")
	@ApiImplicitParam(name = "id", value = "Day off id", required = true, dataType = "long", paramType = "path")
    public ResponseEntity<String> delete(@PathVariable Long id){
		dayOffService.delete(id);
        return new ResponseEntity<>("Usunięto z bazy dzień wolny.", HttpStatus.OK);
    }

}
