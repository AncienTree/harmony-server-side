package pl.entpoint.harmony.service.settings.contractType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.entpoint.harmony.entity.settings.ContractType;

/**
 * @author Mateusz Dąbek
 * @created 19/01/2020
 */

@RestController
@RequestMapping("/api/setting/contract")
@CrossOrigin(origins = "http://localhost:4200")
public class ContractTypeController {
	
	ContractTypeService contractTypeService;
	
	@Autowired
	public ContractTypeController(ContractTypeService contractTypeService) {
		this.contractTypeService = contractTypeService;
	}

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
}
