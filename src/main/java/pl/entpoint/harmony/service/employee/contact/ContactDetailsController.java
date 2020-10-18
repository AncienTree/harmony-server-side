package pl.entpoint.harmony.service.employee.contact;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.entpoint.harmony.entity.employee.ContactDetails;
import pl.entpoint.harmony.entity.pojo.controller.ContactPojo;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@RestController
@RequestMapping("/api/employee/contact")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Api(tags = "Employee Contact Controller")
public class ContactDetailsController {

    private final ContactDetailsService contactDetailsService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Get employee contact information by id.", nickname = "Get employee contact information by id.")
    @ApiImplicitParam(name = "id", value = "Employee contact id", required = true, dataType = "Long", paramType = "path")
    public ContactDetails getContact(@PathVariable Long id){
        return contactDetailsService.getContactDetails(id);
    }

    @PatchMapping("/")
    @ApiOperation(value = "Change employee contact information.", nickname = "Change employee contact information.")
    public ResponseEntity<String> saveChange(@RequestBody ContactPojo contactDetails){

        contactDetailsService.change(contactDetails);
        return new ResponseEntity<>("Dane kontakotwe zostały zapisane.", HttpStatus.OK);
    }
}
