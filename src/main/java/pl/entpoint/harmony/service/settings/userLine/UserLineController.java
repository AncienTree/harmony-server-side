package pl.entpoint.harmony.service.settings.userLine;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.entpoint.harmony.entity.pojo.controller.LinesPojo;
import pl.entpoint.harmony.entity.settings.UserLine;

/**
 * @author Mateusz Dąbek
 * @created 20 maj 2020
 * 
 */

@RestController
@RequestMapping("/api/setting/userline")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Api(tags = "User Lines Controller")
public class UserLineController {
	private final UserLineService userLineService;

	@GetMapping("/{name}")
	@ApiOperation(value = "Get line by name.", nickname = "Get line by name.")
	@ApiImplicitParam(name = "name", value = "Line name", required = true, dataType = "String", paramType = "path")
	public UserLine getLine(@PathVariable String name) {
		return userLineService.getByName(name);
	}
	
	@GetMapping("/")
	@ApiOperation(value = "Get all lines.", nickname = "Get all lines.")
	public List<UserLine> getLines() {
		return userLineService.getAll();
	}
	
	@PostMapping("/")
	@ApiOperation(value = "Create new line.", nickname = "Create new line.")
	@ApiImplicitParam(name = "line", value = "Line body", required = true, dataType = "LinesPojo", paramType = "body")
	public ResponseEntity<String> create(@RequestBody LinesPojo line) {
		UserLine userLine = new UserLine(line);
		userLineService.save(userLine);
        return new ResponseEntity<>("Dodano nową linię.", HttpStatus.CREATED);
	}
	
	@PutMapping("/")
	@ApiOperation(value = "Update line.", nickname = "Update line.")
	@ApiImplicitParam(name = "line", value = "Line body", required = true, dataType = "LinesPojo", paramType = "body")
	public ResponseEntity<String> update(@RequestBody LinesPojo line) {
		userLineService.change(line);
        return new ResponseEntity<>("Zmieniono nazwę linii", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete line.", nickname = "Delete line.")
	@ApiImplicitParam(name = "id", value = "Line id", required = true, dataType = "long", paramType = "path")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		userLineService.delete(id);
        return new ResponseEntity<>("Usunięto linię.", HttpStatus.OK);
	}
	
}
