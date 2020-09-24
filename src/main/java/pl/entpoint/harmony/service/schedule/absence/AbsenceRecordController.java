package pl.entpoint.harmony.service.schedule.absence;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.entpoint.harmony.entity.pojo.AbsencePojo;
import pl.entpoint.harmony.entity.schedule.AbsenceRecord;


/**
 * @author Mateusz Dąbek
 * @created 22 wrz 2020
 * 
 */

@RestController
@RequestMapping("/api/schedule/absence")
@CrossOrigin(origins = "http://localhost:4200")
public class AbsenceRecordController {
	
	private final AbsenceRecordService absenceRecordService;

	@Autowired
	public AbsenceRecordController(AbsenceRecordService absenceRecordService) {
		this.absenceRecordService = absenceRecordService;
	}
	
	@GetMapping("")
	public List<AbsenceRecord> getAllRequests() {
		return absenceRecordService.getAll();
	}
	
	@GetMapping("{id}")
	public List<AbsenceRecord> getEmployeeRequests(@PathVariable Long id) {
		return absenceRecordService.getEmployeeRequests(id);
	}
	
	@GetMapping("my")
	public List<AbsenceRecord> getMyRequests(Principal principal) {
		return absenceRecordService.getMyRequests(principal);
	}
	
	@PostMapping("")
	public ResponseEntity<String> submiteRequest(@RequestBody List<AbsencePojo> record, Principal principal) {
		absenceRecordService.submiteRequest(record, principal.getName());
		
		return new ResponseEntity<>("Utworzono nowy wniosek urlopowy", HttpStatus.CREATED);
	}
	
	@PostMapping("accept")
	public ResponseEntity<String> acceptRequest(@RequestBody Long id, Principal principal) {
		absenceRecordService.acceptRequest(id, principal);
		
		return new ResponseEntity<>("Zaakceptowano wniosek urlopowy", HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("decline")
	public ResponseEntity<String> declineRequest(@RequestBody Long id) {
		absenceRecordService.declineRequest(id);
		
		return new ResponseEntity<>("Odrzucono wniosek urlopowy", HttpStatus.OK);
	}
}
