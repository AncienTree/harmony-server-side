package pl.entpoint.harmony.util.exception.setting;

/**
 * @author Mateusz Dąbek
 * @created 19 maj 2020
 * 
 */

public class ContractTypeNotFoundException extends RuntimeException {

	public ContractTypeNotFoundException(Long id) {
		super("Nie znaleziono typu umowy pod takim id:" + id);
	}	
	
}
