package pl.entpoint.harmony.service.employee.contact;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.entpoint.harmony.entity.employee.ContactDetails;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@RestController
@RequestMapping("/api/employee/contact")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class ContactDetailsController {

    private ContactDetailsService contactDetailsService;

    @Autowired
    public ContactDetailsController(ContactDetailsService contactDetailsService) {
        this.contactDetailsService = contactDetailsService;
    }

    @GetMapping("/{id}")
    public ContactDetails getContact(@PathVariable Long id){
        return contactDetailsService.getContactDetails(id);
    }

    @PatchMapping("/")
    public ResponseEntity<String> saveChange(@RequestBody ContactDetails contactDetails){

        contactDetailsService.change(contactDetails);
        return new ResponseEntity<>("Dane kontakotwe zostały zapisane.", HttpStatus.OK);
    }
}
