package pl.entpoint.harmony.service.settings.contractType;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.entpoint.harmony.entity.settings.ContractType;

/**
 * @author Mateusz Dąbek
 * @created 19/01/2020
 */

@RestController
@RequestMapping("/api/setting/contract")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class ContractTypeController {
	final ContractTypeService contractTypeService;

	@GetMapping("/{id}")
	public ContractType getContract(@PathVariable Long id) {
		return contractTypeService.getContractType(id);
	}
	
	@GetMapping("/")
	public List<ContractType> getContracts() {
		return contractTypeService.getContractTypes();
	}
	
	@PostMapping("/")
	public ResponseEntity<String> createContract(@RequestBody ContractType contract) {
		contractTypeService.createContractType(contract);
		
		return new ResponseEntity<>("Umowa " + contract.getName() + " została zapisana", HttpStatus.CREATED);
	}

	@PatchMapping("/")
	public ResponseEntity<String> update(@RequestBody Map<String, String> contract) {
		contractTypeService.change(contract);
		return new ResponseEntity<>("Zmieniono nazwę umowy", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		contractTypeService.delete(id);
		return new ResponseEntity<>("Usunięto umowę.", HttpStatus.OK);
	}
}
