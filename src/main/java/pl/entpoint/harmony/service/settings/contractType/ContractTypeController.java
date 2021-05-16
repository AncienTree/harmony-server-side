package pl.entpoint.harmony.service.settings.contractType;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.entpoint.harmony.entity.pojo.controller.ContractPojo;
import pl.entpoint.harmony.entity.settings.ContractType;

/**
 * @author Mateusz Dąbek
 * @created 19/01/2020
 */

@RestController
@RequestMapping("/api/setting/contract")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Api(tags = "Contracts Controller")
public class ContractTypeController {
	final ContractTypeService contractTypeService;

	@GetMapping("/{id}")
	@ApiOperation(value = "Get contract by id.", nickname = "Get contract by id.")
	@ApiImplicitParam(name = "id", value = "Contract id", required = true, dataType = "long", paramType = "path")
	public ContractType getContract(@PathVariable Long id) {
		return contractTypeService.getContractType(id);
	}
	
	@GetMapping("/")
	@ApiOperation(value = "Get all contracts.", nickname = "Get all contracts.")
	public List<ContractType> getContracts() {
		return contractTypeService.getContractTypes();
	}
	
	@PostMapping("/")
	@ApiOperation(value = "Create new contract.", nickname = "Create new contract.")
	@ApiImplicitParam(name = "contract", value = "Contract body", required = true, dataType = "ContractPojo", paramType = "body")
	public ResponseEntity<String> createContract(@RequestBody ContractPojo contract) {
		ContractType contractType = new ContractType(contract);

		contractTypeService.createContractType(contractType);
		
		return new ResponseEntity<>(String.format("Umowa %s została zapisana", contract.getName()), HttpStatus.CREATED);
	}

	@PutMapping("/")
	@ApiOperation(value = "Update contract.", nickname = "Update contract.")
	@ApiImplicitParam(name = "contract", value = "Contract body", required = true, dataType = "ContractPojo", paramType = "body")
	public ResponseEntity<String> update(@RequestBody ContractPojo contract) {
		contractTypeService.change(contract);
		return new ResponseEntity<>("Zmieniono nazwę umowy", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete contract by id.", nickname = "Delete contract by id.")
	@ApiImplicitParam(name = "id", value = "Contract id", required = true, dataType = "long", paramType = "path")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		contractTypeService.delete(id);
		return new ResponseEntity<>("Usunięto umowę.", HttpStatus.OK);
	}
}
