package pl.entpoint.harmony.service.settings.userSection;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class UserSectionController {
	private final UserSectionService userSectionService;

	@GetMapping("/")
	public List<UserSection> getSections() {
		return userSectionService.getAllActive();
	}
	
	@GetMapping("/all")
	public List<UserSection> getAllSections() {
		return userSectionService.getAll();
	}
	
	@GetMapping("/{name}")
	public UserSection getSections(@PathVariable String name) {
		return userSectionService.getAllActiveLider(name);
	}
	
	@PostMapping("/")
	public ResponseEntity<String> create(@RequestBody UserSection section) {
		if (userSectionService.checkSection(section)) {
			return new ResponseEntity<>("Podany lider ma już aktywną sekcję.", HttpStatus.BAD_REQUEST);
		}

		userSectionService.save(section);
        return new ResponseEntity<>("Dodano nową sekcję.", HttpStatus.CREATED);
	}
	
	@PatchMapping("/")
	public ResponseEntity<String> update(@RequestBody Map<String, String> section) {
		userSectionService.change(section);
        return new ResponseEntity<>("Zmieniono dane sekcjii.", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		userSectionService.delete(id);
        return new ResponseEntity<>("Usunięto sekcję.", HttpStatus.OK);
	}
}
