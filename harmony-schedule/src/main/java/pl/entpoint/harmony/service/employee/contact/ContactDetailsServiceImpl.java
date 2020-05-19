package pl.entpoint.harmony.service.employee.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.employee.ContactDetails;
import pl.entpoint.harmony.util.exception.employee.EmployeeNotFoundException;

import java.util.Map;
import java.util.Optional;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@Service
public class ContactDetailsServiceImpl implements ContactDetailsService {

    ContactDetailsRepository contactDetailsRepository;

    @Autowired
    public ContactDetailsServiceImpl(ContactDetailsRepository contactDetailsRepository) {
        this.contactDetailsRepository = contactDetailsRepository;
    }

    @Override
    public ContactDetails getContactDetails(Long id) {
        return contactDetailsRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public void change(Map<String, String> contactDetails) {
        Long id = Long.parseLong(contactDetails.get("id"));
        Optional<ContactDetails> contact = Optional.ofNullable(contactDetailsRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id)));
        ContactDetails cd = null;
        if (contact.isPresent()){
            cd = contact.get();
        }
        // Zmiany
        assert cd != null;

        cd.setAddress(contactDetails.get("address"));
        cd.setCity(contactDetails.get("city"));
        cd.setZipCode(contactDetails.get("zipCode"));
        cd.setPhoneNumber(contactDetails.get("phone"));
        cd.setContactName(contactDetails.get("contact"));
        cd.setContactPhoneNumber(contactDetails.get("contactPhone"));

        contactDetailsRepository.save(cd);
    }
}
