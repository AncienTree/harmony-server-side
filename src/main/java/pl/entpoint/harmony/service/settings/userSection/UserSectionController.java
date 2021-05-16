package pl.entpoint.harmony.service.settings.userSection;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.entpoint.harmony.entity.pojo.controller.SectionsPojo;
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
		return userSectionService.getAllActiveLeader(name);
	}
	
	@PostMapping("/")
	@ApiOperation(value = "Create new section.", nickname = "Create new section.")
	@ApiImplicitParam(name = "section", value = "Section body", required = true, dataType = "UserSection", paramType = "body")
	public ResponseEntity<String> create(@RequestBody SectionsPojo section) {
		UserSection userSection = new UserSection(section);
		if (userSectionService.checkSection(userSection)) {
			return new ResponseEntity<>("Podany lider ma już aktywną sekcję.", HttpStatus.BAD_REQUEST);
		}

		userSectionService.save(userSection);
        return new ResponseEntity<>("Dodano nową sekcję.", HttpStatus.CREATED);
	}
	
	@PutMapping("/")
	@ApiOperation(value = "Update section.", nickname = "Update section.")
	@ApiImplicitParam(name = "section", value = "Section body", required = true, dataType = "SectionsPojo", paramType = "body")
	public ResponseEntity<String> update(@RequestBody SectionsPojo section) {
		userSectionService.change(section);
        return new ResponseEntity<>("Zmieniono dane sekcji.", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete section.", nickname = "Delete section.")
	@ApiImplicitParam(name = "id", value = "Section id", required = true, dataType = "long", paramType = "path")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		userSectionService.delete(id);
        return new ResponseEntity<>("Usunięto sekcję.", HttpStatus.OK);
	}
}
