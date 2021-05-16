package pl.entpoint.harmony.util.exception.setting;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mateusz Dąbek
 * @created 19 maj 2020
 * 
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Contract Type Not Found.")
public class ContractTypeNotFoundException extends RuntimeException {
	public ContractTypeNotFoundException(Long id) {
		super(String.format("Nie znaleziono typu umowy pod takim id:%d", id));
	}	
	
}
