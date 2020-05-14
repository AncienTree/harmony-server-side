package pl.entpoint.harmony.service.employee.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.employee.ContactDetails;
import pl.entpoint.harmony.util.exception.employee.EmployeeNotFoundException;

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
    public void change(ContactDetails contactDetails) {
        Optional<ContactDetails> contact = Optional.ofNullable(contactDetailsRepository.findById(contactDetails.getId())
                .orElseThrow(() -> new EmployeeNotFoundException(contactDetails.getId())));
        ContactDetails cd = null;
        if (contact.isPresent()){
            cd = contact.get();
        }
        // Zmiany
        assert cd != null;

        cd.setAddress(contactDetails.getAddress());
        cd.setCity(contactDetails.getCity());
        cd.setZipCode(contactDetails.getZipCode());
        cd.setPhoneNumber(contactDetails.getPhoneNumber());
        cd.setContactName(contactDetails.getContactName());
        cd.setContactPhoneNumber(contactDetails.getContactPhoneNumber());

        contactDetailsRepository.save(cd);
    }
}
