package pl.entpoint.harmony.service.settings.contractType;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.pojo.controller.ContractPojo;
import pl.entpoint.harmony.entity.settings.ContractType;
import pl.entpoint.harmony.util.exception.setting.ContractTypeNotFoundException;

import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 19/01/2020
 */

@Service
@AllArgsConstructor
public class ContractTypeServiceImpl implements ContractTypeService{
	
	final ContractTypeRepository contractTypeRepository;

	@Override
    public List<ContractType> getContractTypes() {
        return contractTypeRepository.findAll();
    }

    @Override
    public ContractType getContractType(Long id) {
    	return contractTypeRepository.findById(id)
                .orElseThrow(() -> new ContractTypeNotFoundException(id));
    }
    
    @Override
    public void createContractType(ContractType contract) {  	
    	contractTypeRepository.save(contract);
    }

    @Override
    public void change(ContractPojo contract) {
        ContractType contractType = contractTypeRepository.findById(contract.getId())
                .orElseThrow(() -> new ContractTypeNotFoundException(contract.getId()));

        contractType.setName(contract.getName());
        contractTypeRepository.save(contractType);
    }

    @Override
    public void delete(Long id) {
        ContractType contractType = contractTypeRepository.findById(id)
                .orElseThrow(() -> new ContractTypeNotFoundException(id));
        contractTypeRepository.delete(contractType);
    }
}
