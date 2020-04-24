package pl.entpoint.harmony.entity.employee;

import javax.persistence.*;

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
@Table(name = "employee_leave", schema = "employee")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeLeave extends AuditEntity implements Serializable {
	private static final long serialVersionUID = 3145073070391952073L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private int total;

    private int normal;

    private int uz;

    private int additional;

    @Column(name = "past_years")
    private int pastYears;

}
