package pl.entpoint.harmony.service.employee.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.entpoint.harmony.entity.employee.ContactDetails;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Long> {
}
