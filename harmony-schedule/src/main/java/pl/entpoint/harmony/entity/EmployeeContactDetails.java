package pl.entpoint.harmony.entity;

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

/**
 * @author Mateusz Dąbek
 * Created on Nov 14, 2019
 * m.dabek@entpoint.pl
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "contact_details")
@Getter @Setter @NoArgsConstructor
public class EmployeeContactDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String address;
	
	private String city;
	
	//TODO walidacja kodu pocztowego
	@Column(name = "zip_code", length = 6)
	private String zipCode;
	
	@Column(name = "phone_number")
	private int phoneNumber;
	
	@Column(name = "contact_phone_numbere")
	private int contactPhoneNumber;
	
	@Column(name = "contact_name")
	private String contactName;
		
}
