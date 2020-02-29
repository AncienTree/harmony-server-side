package pl.entpoint.harmony.entity.employee;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;

/**
 * @author Mateusz Dąbek
 * @created 11/11/2019
 */

@Entity
@Table(name = "employees")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@NoArgsConstructor
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 20)
    private String firstName;

    @Column(name = "last_name", length = 40)
    private String lastName;

    @Column(length = 11, unique = true)
    private long pesel;

    @Column(length = 1)
    private String sex;

    private LocalDate birthday;

    @Email
    private String email;

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

    private boolean created;

    @Column(name = "create_date")
    private LocalDate createDate;

    public Employee(String firstName, String lastName, long pesel, String sex, LocalDate birthday, String position,
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
        this.createDate = LocalDate.now();
    }
}
	
	