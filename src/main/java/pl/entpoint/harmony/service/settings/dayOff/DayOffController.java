package pl.entpoint.harmony.service.settings.dayOff;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.entpoint.harmony.entity.settings.DayOff;

/**
 * @author Mateusz Dąbek
 * @created 19 maj 2020
 * 
 */

@RestController
@RequestMapping("/api/setting/dayoff")
@CrossOrigin(origins = "http://localhost:4200")
public class DayOffController {
	
	final DayOffService dayOffService;

	@Autowired
	public DayOffController(DayOffService dayOffService) {
		this.dayOffService = dayOffService;
	}
	
	@GetMapping("/{year}")
	public List<DayOff> getDayOffByYear(@PathVariable String year){
		return dayOffService.getDayOffByYear(year);
	}
	
	@PostMapping("/between")
	public List<DayOff> getDayOffBetween(@RequestBody Map<String, LocalDate> body){
		return dayOffService.getDayOffBetweenDats(body.get("start"), body.get("end"));
	}
	
	@PostMapping("/")
    public ResponseEntity<String> saveChange(@RequestBody DayOff day){
		dayOffService.create(day);
        return new ResponseEntity<>("Dodano nowy dzień wolny.", HttpStatus.CREATED);
    }
	
	@PatchMapping("/")
    public ResponseEntity<String> update(@RequestBody DayOff day){
		dayOffService.update(day);
		
        return new ResponseEntity<>("Zaktualizowano opis.", HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
		dayOffService.delete(id);
        return new ResponseEntity<>("Usunięto z bazy dzień wolny.", HttpStatus.OK);
    }

}
