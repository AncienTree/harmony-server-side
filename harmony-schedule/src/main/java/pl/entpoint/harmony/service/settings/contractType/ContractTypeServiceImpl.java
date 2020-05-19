package pl.entpoint.harmony.service.settings.contractType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.settings.ContractType;
import pl.entpoint.harmony.util.exception.ContractTypeNotFoundException;

import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 19/01/2020
 */

@Service
public class ContractTypeServiceImpl implements ContractTypeService{
	
	ContractTypeRepository contractTypeRepository;
	
	@Autowired
    public ContractTypeServiceImpl(ContractTypeRepository contractTypeRepository) {
		super();
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
}
