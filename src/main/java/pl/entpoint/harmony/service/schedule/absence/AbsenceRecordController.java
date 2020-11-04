package pl.entpoint.harmony.service.schedule.absence;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.entpoint.harmony.entity.pojo.controller.AbsencePojo;
import pl.entpoint.harmony.entity.schedule.AbsenceRecord;


/**
 * @author Mateusz Dąbek
 * @created 22 wrz 2020
 * 
 */

@RestController
@RequestMapping("/api/schedule/absence")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Api(tags = "Absence Controller")
public class AbsenceRecordController {
	
	private final AbsenceRecordService absenceRecordService;

	@GetMapping("/")
	@ApiOperation(value = "Get all new absence requests.", nickname = "Get all new absence requests.")
	public List<AbsenceRecord> getAllRequests() {
		return absenceRecordService.getAll();
	}
	
	@GetMapping("/employee/{id}")
	@ApiOperation(value = "Get employee absence request by employee id.", nickname = "Get employee absence request by employee id.")
	@ApiImplicitParam(name = "id", value = "Request id", required = true, dataType = "long", paramType = "path")
	public List<AbsenceRecord> getEmployeeRequests(@PathVariable Long id) {
		return absenceRecordService.getEmployeeRequests(id);
	}

	@GetMapping("/section/{id}")
	@ApiOperation(value = "Get employees absence request by section id.", nickname = "Get employee absence request by section id.")
	@ApiImplicitParam(name = "id", value = "Request id", required = true, dataType = "long", paramType = "path")
	public List<AbsenceRecord> getSectionRequests(@PathVariable Long id) {
		return absenceRecordService.getSectionRequests(id);
	}
	
	@GetMapping("/my")
	@ApiOperation(value = "Get all my absence requests.", nickname = "Get all my absence requests.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "year", value = "Year in string", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "opt", value = "all, new, declined, accepted", required = true, dataType = "string", paramType = "query"),
	})
	public List<AbsenceRecord> getMyRequests(@RequestParam String year, @RequestParam String opt, Principal principal) {
		return absenceRecordService.getMyRequests(year, opt, principal);
	}
	
	@PostMapping("/")
	@ApiOperation(value = "Submit new absence requests.", nickname = "Submit new absence requests.")
	@ApiImplicitParam(name = "record", value = "List of record", required = true, dataType = "AbsencePojo", paramType = "body")
	public ResponseEntity<String> submitRequest(@RequestBody List<AbsencePojo> record, Principal principal) {
		absenceRecordService.submitRequest(record, principal.getName());
		
		return new ResponseEntity<>("Utworzono nowy wniosek urlopowy", HttpStatus.CREATED);
	}
	
	@PostMapping("/accept")
	@ApiOperation(value = "Accept absence request.", nickname = "Accept absence request.")
	@ApiImplicitParam(name = "id", value = "Request id", required = true, dataType = "long", paramType = "body")
	public ResponseEntity<String> acceptRequest(@RequestBody Long id) {
		absenceRecordService.acceptRequest(id);
		
		return new ResponseEntity<>("Zaakceptowano wniosek urlopowy", HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/decline")
	@ApiOperation(value = "Decline absence request.", nickname = "Decline absence request.")
	@ApiImplicitParam(name = "absence", value = "Absence body", dataType = "AbsencePojo" ,required = true, paramType = "body")
	public ResponseEntity<String> declineRequest(@RequestBody AbsencePojo absence) {
		absenceRecordService.declineRequest(absence.getId(), absence.getText());
		
		return new ResponseEntity<>("Odrzucono wniosek urlopowy", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete absence request.", nickname = "Delete absence request.")
	@ApiImplicitParam(name = "id", value = "Request id", required = true, dataType = "long", paramType = "path")
	public ResponseEntity<String> deleteRequest(@PathVariable Long id) {
		absenceRecordService.deleteRequest(id);

		return new ResponseEntity<>("Usunięto wniosek urlopowy", HttpStatus.OK);
	}
}
