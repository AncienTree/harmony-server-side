package pl.entpoint.harmony.entity.employee;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;

/**
 * @author Mateusz Dąbek
 * @created 14/11/2019
 */


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "contact_details", schema = "employee")
@Getter @Setter @NoArgsConstructor
public class ContactDetails extends AuditEntity {
	@Id
    @SequenceGenerator(name="contact_details_sqe", sequenceName="employee.contact_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="contact_details_sqe")
    private Long id;

    private String address;

    private String city;

    @Column(name = "zip_code")
    @Size(min = 6, max = 6)
    private String zipCode;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "contact_phone_number")
    private String contactPhoneNumber;

    @Column(name = "contact_name")
    private String contactName;
    
    public void fire() {
    	this.address = null;
    	this.city = null;
    	this.zipCode = null;
    	this.phoneNumber = null;
    	this.contactPhoneNumber = null;
    	this.contactName = null;
    }

}
