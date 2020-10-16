package pl.entpoint.harmony.service.settings.userSection;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "User Sections Controller")
public class UserSectionController {
	private final UserSectionService userSectionService;

	@GetMapping("/")
	@ApiOperation(value = "Get all active sections.", nickname = "Get all active sections.")
	public List<UserSection> getSections() {
		return userSectionService.getAllActive();
	}

	@GetMapping("/all")
	@ApiOperation(value = "Get all sections.", nickname = "Get all sections.")
	public List<UserSection> getAllSections() {
		return userSectionService.getAll();
	}
	
	@GetMapping("/{name}")
	@ApiOperation(value = "Get section by name.", nickname = "Get section by name.")
	@ApiImplicitParam(name = "name", value = "Section name", required = true, dataType = "String", paramType = "path")
	public UserSection getSections(@PathVariable String name) {
		return userSectionService.getAllActiveLider(name);
	}
	
	@PostMapping("/")
	@ApiOperation(value = "Create new section.", nickname = "Create new section.")
	@ApiImplicitParam(name = "section", value = "Section body", required = true, dataType = "UserSection", paramType = "body")
	public ResponseEntity<String> create(@RequestBody UserSection section) {
		if (userSectionService.checkSection(section)) {
			return new ResponseEntity<>("Podany lider ma już aktywną sekcję.", HttpStatus.BAD_REQUEST);
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
