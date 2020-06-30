package pl.entpoint.harmony.entity.schedule;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.entpoint.harmony.auditing.AuditEntity;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.dto.SimpleEmployee;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "schedule_summary", schema = "schedule")
@Getter @Setter @NoArgsConstructor @ToString
public class ScheduleSummary extends AuditEntity implements Serializable {
	private static final long serialVersionUID = -7850728271314700401L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    @ToString.Exclude
    private Employee employee;

    @Transient
    @ToString.Exclude
    private SimpleEmployee simpleEmployee;

    @OneToMany
    @JoinTable(
            name = "schedule_mapping",
            schema = "schedule",
            joinColumns = {@JoinColumn(name = "schedule_summary_id")},
            inverseJoinColumns = {@JoinColumn(name = "schedule_record_id")}
            )
    private List<ScheduleRecord> scheduleRecords;

    private Date scheduleDate;

	public ScheduleSummary(Employee employee, String scheduleDate) {
		this.employee = employee;
		this.simpleEmployee = new SimpleEmployee(employee);
		this.scheduleDate = Date.valueOf(scheduleDate);
	}
    
    
}
