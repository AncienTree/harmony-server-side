package pl.entpoint.harmony.service.settings.contractType;

import pl.entpoint.harmony.entity.settings.ContractType;

import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 19/01/2020
 */
public interface ContractTypeService {

    List<ContractType> getContractTypes();
    ContractType getContractType(Long id);
    void createContractType(ContractType contract);
}
