package pl.entpoint.harmony.service.settings.contractType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.settings.ContractType;
import pl.entpoint.harmony.util.exception.ContractTypeNotFoundException;

import java.util.List;
import java.util.Map;

/**
 * @author Mateusz Dąbek
 * @created 19/01/2020
 */

@Service
public class ContractTypeServiceImpl implements ContractTypeService{
	
	final ContractTypeRepository contractTypeRepository;
	
	@Autowired
    public ContractTypeServiceImpl(ContractTypeRepository contractTypeRepository) {
		this.contractTypeRepository = contractTypeRepository;
	}

	@Override
    public List<ContractType> getContractTypes() {
        return contractTypeRepository.findAll();
    }

    @Override
    public ContractType getContractType(Long id) {
    	return contractTypeRepository.findById(id).orElseThrow(() -> new ContractTypeNotFoundException(id));    	
    }
    
    @Override
    public void createContractType(ContractType contract) {
    	contractTypeRepository.save(contract);
    }

    @Override
    public void change(Map<String, String> contract) {
        ContractType contractType = contractTypeRepository.findById(Long.parseLong(contract.get("id")))
                .orElseThrow(() -> new RuntimeException("Nie znaleziono podanej umowy."));

        contractType.setName(contract.get("name"));
        contractTypeRepository.save(contractType);
    }

    @Override
    public void delete(Long id) {
        ContractType contractType = contractTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono umowy pod tym id."));
        contractTypeRepository.delete(contractType);
    }
}
