package pl.entpoint.harmony.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Mateusz Dąbek
 * Created on Nov 11, 2019
 * m.dabek@entpoint.pl
 */

@Entity
@Table(name = "employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(length = 11, unique = true)
	private String pesel;

	private String email;
	
	private String position;
	
	@Column(name = "contract_position")
	private String contractPosition;
	
	@Column(name = "work_status")
	private String workStatus;
	
	@Column(name = "contract_type")
	private String contractType;
	
	@Column(name = "basic_unit")
	private String basicUnit;
	
	private float unit; 
	
	@Column(name = "start_work_date")
	private Date startWorkDate;
	
	@Column(name = "end_work_date")
	private Date endWorkDate;
	
	@Column(name = "start_contract_date")
	private Date startContractDate;
	
	@Column(name = "end_contract_date")
	private Date endContractDate;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_details_id")
	private EmployeeDetails emplDetails;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_details_id")
	private EmployeeContactDetails emplContactDetails;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_info_id")
	private EmployeeInfo emplInfo;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_leave_id")
	private EmployeeLeave emplLeave;
		
	public Employee() {
		this.emplDetails = new EmployeeDetails();
		this.emplContactDetails = new EmployeeContactDetails();
		this.emplInfo = new EmployeeInfo();
		this.emplLeave = new EmployeeLeave();
	}
				
	public Employee(String firstName, String lastName, String pesel) {	
		this();
		this.firstName = firstName;
		this.lastName = lastName;
		this.pesel = pesel;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getContractPosition() {
		return contractPosition;
	}

	public void setContractPosition(String contractPosition) {
		this.contractPosition = contractPosition;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getBasicUnit() {
		return basicUnit;
	}

	public void setBasicUnit(String basicUnit) {
		this.basicUnit = basicUnit;
	}

	public float getUnit() {
		return unit;
	}

	public void setUnit(float unit) {
		this.unit = unit;
	}

	public Date getStartWorkDate() {
		return startWorkDate;
	}

	public void setStartWorkDate(Date startWorkDate) {
		this.startWorkDate = startWorkDate;
	}

	public Date getEndWorkDate() {
		return endWorkDate;
	}

	public void setEndWorkDate(Date endWorkDate) {
		this.endWorkDate = endWorkDate;
	}

	public Date getStartContractDate() {
		return startContractDate;
	}

	public void setStartContractDate(Date startContractDate) {
		this.startContractDate = startContractDate;
	}

	public Date getEndContractDate() {
		return endContractDate;
	}

	public void setEndContractDate(Date endContractDate) {
		this.endContractDate = endContractDate;
	}

	public EmployeeDetails getEmplDetails() {
		return emplDetails;
	}

	public void setEmplDetails(EmployeeDetails emplDetails) {
		this.emplDetails = emplDetails;
	}

	public EmployeeContactDetails getEmplContactDetails() {
		return emplContactDetails;
	}

	public void setEmplContactDetails(EmployeeContactDetails emplContactDetails) {
		this.emplContactDetails = emplContactDetails;
	}

	public EmployeeInfo getEmplInfo() {
		return emplInfo;
	}

	public void setEmplInfo(EmployeeInfo emplInfo) {
		this.emplInfo = emplInfo;
	}

	public EmployeeLeave getEmplLeave() {
		return emplLeave;
	}

	public void setEmplLeave(EmployeeLeave emplLeave) {
		this.emplLeave = emplLeave;
	}

	public void newEmployee() {
		
	}
	
}
	
	