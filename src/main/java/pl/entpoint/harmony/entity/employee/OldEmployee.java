package pl.entpoint.harmony.entity.employee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 30.06.2020
 */

@Entity
@Table(name = "old", schema = "employee")
@Getter @Setter @NoArgsConstructor
public class OldEmployee extends AuditEntity implements Serializable {
    private static final long serialVersionUID = -7862141001762074429L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "full_name")
    private String name;

    @Column(unique = true)
    @Size(max = 40)
    @NotNull
    private String pesel;

    private String position;

    @Column(name = "user_section")
    private String userSection;

    @Column(name = "lt_login")
    private String ltLogin;

    @Column(name = "lt_id")
    @Size(max = 8)
    private String ltId;

    @Column(name = "end_work_date")
    private LocalDate endWorkDate;

    public OldEmployee(Employee employee, EmployeeDetails employeeDetails) {
        this.name = employee.getFirstName() + " " + employee.getLastName();
        this.pesel = employee.getPesel();
        this.position = employee.getPosition();
        this.userSection = employeeDetails.getUserSection();
        this.ltLogin = employeeDetails.getLtLogin();
        this.ltId = employeeDetails.getLtId();
        this.endWorkDate = LocalDate.now();
    }
}
