package pl.entpoint.harmony.entity.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;

import java.io.Serializable;

/**
 * @author Mateusz Dąbek
 * @created 14/11/2019
 */


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "contact_details", schema = "employee")
@Getter
@Setter
@NoArgsConstructor
public class ContactDetails extends AuditEntity implements Serializable {
	private static final long serialVersionUID = 7373634928251884422L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String city;

    @Column(name = "zip_code", length = 6)
    private String zipCode;

    @Column(name = "phone_number")
    private int phoneNumber;

    @Column(name = "contact_phone_number")
    private int contactPhoneNumber;

    @Column(name = "contact_name")
    private String contactName;

}
