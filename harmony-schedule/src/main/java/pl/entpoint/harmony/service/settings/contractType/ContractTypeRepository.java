package pl.entpoint.harmony.service.settings.contractType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.entpoint.harmony.models.settings.ContractType;

/**
 * @author Mateusz Dąbek
 * @created 19/01/2020
 */

@Repository
public interface ContractTypeRepository extends JpaRepository<ContractType, Long> {
}
