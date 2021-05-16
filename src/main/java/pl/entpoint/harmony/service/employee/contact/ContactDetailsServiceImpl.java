package pl.entpoint.harmony.service.employee.contact;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.employee.ContactDetails;
import pl.entpoint.harmony.entity.pojo.controller.ContactPojo;
import pl.entpoint.harmony.util.exception.employee.EmployeeNotFoundException;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@Service
@AllArgsConstructor
public class ContactDetailsServiceImpl implements ContactDetailsService {

    final ContactDetailsRepository contactDetailsRepository;

    @Override
    public ContactDetails getContactDetails(Long id) {
        return contactDetailsRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public void change(ContactPojo contactDetails) {
        ContactDetails contact = contactDetailsRepository.findById(contactDetails.getId())
                .orElseThrow(() -> new EmployeeNotFoundException(contactDetails.getId()));

        contact.setAddress(contactDetails.getAddress());
        contact.setCity(contactDetails.getCity());
        contact.setZipCode(contactDetails.getZipCode());
        contact.setPhoneNumber(contactDetails.getPhoneNumber());
        contact.setContactName(contactDetails.getContactName());
        contact.setContactPhoneNumber(contactDetails.getContactPhoneNumber());

        contactDetailsRepository.save(contact);
    }
}
