package pl.entpoint.harmony.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
	
	// TODO Sprawdzić czy potrzebne są te wszystkie pola. Ewentualnie stworzyć osobna tabele na dodatkowe dane o pracowniku.
	// TODO Użyć biblioteki lombok
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "lt_login", nullable = false, unique = true)
	private String ltLogin;
	
	@Column(name = "lt_id", nullable = false, unique = true)
	private int ltId;
	
	@Column(name = "crm_login", nullable = false, unique = true)
	private String crmLogin;
	
	@Column(name = "user_line")
	private String userLine;
	
	private String userSection;
	
	private String position;
	
	private String positionDetails;
	
	private String workState;
	
	private float fte;
	
	private Date update;
	
	private String contactType;
	
	private String basicUnit;
	
	private String basic;
	
	private Date workStart;
	
	private Date workEnd;
	
	private Date contractStart;

	private Date contractEnd;
	
	private String contractPosition;
	
	private int pesel;
	
	private int phoneNumber;

	private int contactPhoneNumber;

	private String contactName;

	private String statment;
	
	private String email;
	
	
}
