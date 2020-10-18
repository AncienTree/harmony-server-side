package pl.entpoint.harmony.service.settings.contractType;

import pl.entpoint.harmony.entity.pojo.controller.ContractPojo;
import pl.entpoint.harmony.entity.settings.ContractType;

import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 19/01/2020
 */
public interface ContractTypeService {
    ContractType getContractType(Long id);
    List<ContractType> getContractTypes();
    void createContractType(ContractType contract);
    void change(ContractPojo line);
    void delete(Long id);
}
