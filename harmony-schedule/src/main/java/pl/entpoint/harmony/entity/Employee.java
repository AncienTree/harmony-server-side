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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(length = 11, nullable = false)
	private int pesel;

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

	public int getPesel() {
		return pesel;
	}

	public void setPesel(int pesel) {
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

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", pesel=" + pesel
				+ ", email=" + email + ", position=" + position + ", contractPosition=" + contractPosition
				+ ", workStatus=" + workStatus + ", contractType=" + contractType + ", basicUnit=" + basicUnit
				+ ", unit=" + unit + ", startWorkDate=" + startWorkDate + ", endWorkDate=" + endWorkDate
				+ ", startContractDate=" + startContractDate + ", endContractDate=" + endContractDate + ", emplDetails="
				+ emplDetails + ", emplContactDetails=" + emplContactDetails + ", emplInfo=" + emplInfo + ", emplLeave="
				+ emplLeave + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basicUnit == null) ? 0 : basicUnit.hashCode());
		result = prime * result + ((contractPosition == null) ? 0 : contractPosition.hashCode());
		result = prime * result + ((contractType == null) ? 0 : contractType.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((emplContactDetails == null) ? 0 : emplContactDetails.hashCode());
		result = prime * result + ((emplDetails == null) ? 0 : emplDetails.hashCode());
		result = prime * result + ((emplInfo == null) ? 0 : emplInfo.hashCode());
		result = prime * result + ((emplLeave == null) ? 0 : emplLeave.hashCode());
		result = prime * result + ((endContractDate == null) ? 0 : endContractDate.hashCode());
		result = prime * result + ((endWorkDate == null) ? 0 : endWorkDate.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + pesel;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((startContractDate == null) ? 0 : startContractDate.hashCode());
		result = prime * result + ((startWorkDate == null) ? 0 : startWorkDate.hashCode());
		result = prime * result + Float.floatToIntBits(unit);
		result = prime * result + ((workStatus == null) ? 0 : workStatus.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emplContactDetails == null) {
			if (other.emplContactDetails != null)
				return false;
		} else if (!emplContactDetails.equals(other.emplContactDetails))
			return false;
		if (emplDetails == null) {
			if (other.emplDetails != null)
				return false;
		} else if (!emplDetails.equals(other.emplDetails))
			return false;
		if (emplInfo == null) {
			if (other.emplInfo != null)
				return false;
		} else if (!emplInfo.equals(other.emplInfo))
			return false;
		if (emplLeave == null) {
			if (other.emplLeave != null)
				return false;
		} else if (!emplLeave.equals(other.emplLeave))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (pesel != other.pesel)
			return false;
		return true;
	}
}
	
	