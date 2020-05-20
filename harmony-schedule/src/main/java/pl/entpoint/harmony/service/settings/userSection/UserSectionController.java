package pl.entpoint.harmony.service.settings.userSection;

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

import pl.entpoint.harmony.entity.settings.UserSection;

/**
 * @author Mateusz Dąbek
 * @created 20 maj 2020
 * 
 */

@RestController
@RequestMapping("/api/setting/usersection")
@CrossOrigin(origins = "http://localhost:4200")
public class UserSectionController {
	
	UserSectionService userSectionService;

	@Autowired
	public UserSectionController(UserSectionService userSectionService) {
		this.userSectionService = userSectionService;
	}
	
	@GetMapping("/")
	public List<UserSection> getSections() {
		return userSectionService.getAllActive();
	}
	
	@GetMapping("/{name}")
	public UserSection getSections(@PathVariable String name) {
		return userSectionService.getAllActiveLider(name);
	}
	
	@PostMapping("/")
	public ResponseEntity<String> create(@RequestBody UserSection line) {
		userSectionService.save(line);
        return new ResponseEntity<>("Dodano nową sekcję.", HttpStatus.CREATED);
	}
	
	@PatchMapping("/")
	public ResponseEntity<String> update(@RequestBody Map<String, String> line) {
		userSectionService.change(line);
        return new ResponseEntity<>("Zmieniono dane sekcjii.", HttpStatus.OK);
	}
	
	@DeleteMapping("/")
	public ResponseEntity<String> delete(@RequestBody UserSection line) {
		userSectionService.delete(line);
        return new ResponseEntity<>("Usunięto sekcję " + line.getName(), HttpStatus.OK);
	}
}
