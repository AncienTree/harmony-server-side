package pl.entpoint.harmony.entity.employee;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.entpoint.harmony.auditing.AuditEntity;

/**
 * @author Mateusz Dąbek
 * @created 14/11/2019
 */


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "employee_leave", schema = "employee")
@Getter @Setter @NoArgsConstructor @ToString
public class EmployeeLeave extends AuditEntity {
	@Id
    @SequenceGenerator(name="employee_leave_sqe", sequenceName="employee.employee_leave_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employee_leave_sqe")
    private Long id;

    @Transient
    private int total;

    private int normal;

    private int uz;

    private int additional;

    @Column(name = "past_years")
    private int pastYears;
    
    public void fire() {
    	this.total = 0;
    	this.normal = 0;
    	this.uz = 0;
    	this.additional = 0;
    	this.pastYears = 0;
    	
    }
    
   public int getTotal() {
	   return normal + uz + additional + pastYears;
   }

}
