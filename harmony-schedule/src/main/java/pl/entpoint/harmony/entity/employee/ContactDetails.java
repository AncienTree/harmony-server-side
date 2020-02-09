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

import java.io.Serializable;

/**
 * @author Mateusz Dąbek
 * @created 14/11/2019
 */


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "contact_details")
@Getter
@Setter
@NoArgsConstructor
public class ContactDetails implements Serializable {
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