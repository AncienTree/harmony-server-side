package pl.entpoint.harmony.service.settings.userLine;

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

import pl.entpoint.harmony.entity.settings.UserLine;

/**
 * @author Mateusz Dąbek
 * @created 20 maj 2020
 * 
 */

@RestController
@RequestMapping("/api/setting/userline")
@CrossOrigin(origins = "http://localhost:4200")
public class UserLineController {

	UserLineService userLineService;

	@Autowired
	public UserLineController(UserLineService userLineService) {
		this.userLineService = userLineService;
	}
	
	@GetMapping("/{name}")
	public UserLine getLine(@PathVariable String name) {
		return userLineService.getByName(name);
	}
	
	@GetMapping("/")
	public List<UserLine> getLines() {
		return userLineService.getAll();
	}
	
	@PostMapping("/")
	public ResponseEntity<String> create(@RequestBody UserLine line) {
		userLineService.save(line);
        return new ResponseEntity<>("Dodano nową linię.", HttpStatus.CREATED);
	}
	
	@PatchMapping("/")
	public ResponseEntity<String> update(@RequestBody Map<String, String> line) {
		userLineService.change(line);
        return new ResponseEntity<>("Zmieniono nazwę lini", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		userLineService.delete(id);
        return new ResponseEntity<>("Usunięto linię.", HttpStatus.OK);
	}
	
}
