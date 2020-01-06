package pl.entpoint.harmony.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mateusz Dąbek
 * Created on Nov 11, 2019
 * m.dabek@entpoint.pl
 */

@Entity
@Table(name = "employees")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
@Getter @Setter @NoArgsConstructor
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	@Column(name = "first_name", length = 20)
	private String firstName;
	
	@Column(name = "last_name", length = 40)
	private String lastName;
	
	@Column(length = 11, unique = true)
	private long pesel;
	
	private char sex;
	
	private LocalDate birthday;

	private String email;
	
	private String position;
	
	@Column(name = "contract_position")
	private String contractPosition;
	
	//TODO utworzyć ENUM
	@Column(name = "work_status")
	private String workStatus;
	
	//TODO Osobna tabela do konfiguracji albo ENUM
	@Column(name = "contract_type")
	private String contractType;
	
	//TODO Osobna tabela do konfiguracji 
	@Column(name = "basic_unit")
	private String basicUnit;
	
	private float unit; 
	
	@Column(name = "start_work_date")
	private LocalDate startWorkDate;
	
	@Column(name = "end_work_date")
	private LocalDate endWorkDate;
	
	@Column(name = "start_contract_date")
	private LocalDate startContractDate;
	
	@Column(name = "end_contract_date")
	private LocalDate endContractDate;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_details_id")
	// Zwracanie id zamiast całej encji
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private EmployeeDetails emplDetails;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_details_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private EmployeeContactDetails emplContactDetails;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_info_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private EmployeeInfo emplInfo;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_leave_id")
	//@JsonIgnore
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private EmployeeLeave emplLeave;
	
	@OneToMany(mappedBy = "empl", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Schedule> schedules;
				
	public Employee(String firstName, String lastName, long pesel) {	
		this.emplDetails = new EmployeeDetails();
		this.emplContactDetails = new EmployeeContactDetails();
		this.emplInfo = new EmployeeInfo();
		this.emplLeave = new EmployeeLeave();
		this.firstName = firstName;
		this.lastName = lastName;
		this.pesel = pesel;
	}
		
}
	
	