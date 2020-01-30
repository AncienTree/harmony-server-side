package pl.entpoint.harmony.service.settings.contractType;

import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.settings.ContractType;

import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 19/01/2020
 */

@Service
public class ContractTypeServiceImpl implements ContractTypeService{
    @Override
    public List<ContractType> getContractTypes() {
        return null;
    }

    @Override
    public ContractType getContractType(Long id) {
        return null;
    }
}
