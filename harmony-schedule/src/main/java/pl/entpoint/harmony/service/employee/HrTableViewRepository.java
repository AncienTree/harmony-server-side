package pl.entpoint.harmony.service.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.entpoint.harmony.entity.model.view.HrTable;

/**
 * @author Mateusz Dąbek
 * @created 23/05/2020
 */
public interface HrTableViewRepository extends JpaRepository<HrTable, Long> {

}
