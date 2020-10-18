package pl.entpoint.harmony.entity.employee;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.entpoint.harmony.auditing.AuditEntity;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;
import pl.entpoint.harmony.entity.pojo.controller.EmployeePojo;
import pl.entpoint.harmony.entity.user.User;
import pl.entpoint.harmony.util.EncryptionData;

/**
 * @author Mateusz Dąbek
 * @created 11/11/2019
 */

@Entity
@Table(name = "employees", schema = "employee")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter @Setter @NoArgsConstructor @ToString
public class Employee extends AuditEntity {
	@Id
    @SequenceGenerator(name="employees_sqe", sequenceName="employee.employees_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employees_sqe")
    private Long id;

    @Column(name = "first_name")
    @Size(max = 20)
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @Size(max = 40)
    @NotNull
    private String lastName;

    @Column(unique = true)
    @Size(max = 40)
    @NotNull
    private String pesel;

    @Size(max = 1)
    private String sex;

    private LocalDate birthday;

    @Email
    private String email;
    
    private Float etat;

    private String position;

    @Column(name = "contract_position")
    private String contractPosition;

    @Column(name = "work_status")
    @Enumerated(EnumType.STRING)
    private WorkStatus workStatus;

    @Column(name = "contract_type")
    private String contractType;

    @Column(name = "basic_unit")
    private String basicUnit;

    private String unit;

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
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private EmployeeDetails employeeDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_details_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private ContactDetails contactDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_info_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private EmployeeInfo employeeInfo;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_leave_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private EmployeeLeave employeeLeave;

    @OneToOne(mappedBy = "employee")
    @JsonIgnore
    private User user;

    private boolean created;
    
    @Column(name = "active_account")
    private boolean activeAccount;

    public Employee(String firstName, String lastName, String pesel, String sex, LocalDate birthday, String position,
                    String contractPosition, WorkStatus workStatus, String contractType, String basicUnit, String unit,
                    LocalDate startWorkDate, LocalDate startContractDate) {
        this.employeeDetails = new EmployeeDetails();
        this.contactDetails = new ContactDetails();
        this.employeeInfo = new EmployeeInfo();
        this.employeeLeave = new EmployeeLeave();
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.sex = sex;
        this.etat = 1.0F;
        this.birthday = birthday;
        this.position = position;
        this.contractPosition = contractPosition;
        this.workStatus = workStatus;
        this.contractType = contractType;
        this.basicUnit = basicUnit;
        this.unit = unit;
        this.startWorkDate = startWorkDate;
        this.startContractDate = startContractDate;
        this.created = false;
        this.activeAccount = true;
    }

    public Employee(EmployeePojo pojo) {
        this(
                pojo.getFirstName(),
                pojo.getLastName(),
                EncryptionData.encrypt(pojo.getPesel()),
                pojo.getSex(),
                pojo.getBirthday(),
                pojo.getPosition(),
                pojo.getContractPosition(),
                pojo.getWorkStatus(),
                pojo.getContractType(),
                pojo.getBasicUnit(),
                pojo.getUnit(),
                pojo.getStartWorkDate(),
                pojo.getStartContractDate()
        );
    }

    public void fire() {
    	this.birthday = null;
    	this.email = null;
    	this.position = null;
    	this.contractPosition = null;
    	this.etat = null;
    	this.basicUnit = null;
    	this.unit = null;
    	this.endWorkDate = LocalDate.now();
    	this.startContractDate = null;
    	this.endContractDate = null;
    	
    	this.activeAccount = false;
    	this.workStatus = WorkStatus.NOT_WORK;
    	this.user.setStatus(false);
    }
}
	