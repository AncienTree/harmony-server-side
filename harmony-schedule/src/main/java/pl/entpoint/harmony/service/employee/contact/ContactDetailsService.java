package pl.entpoint.harmony.service.employee.contact;

import pl.entpoint.harmony.entity.employee.ContactDetails;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

public interface ContactDetailsService {
    ContactDetails getContactDetails(Long id);
    void change(ContactDetails contactDetails);
}
