package pl.entpoint.harmony.entity.schedule;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.entpoint.harmony.auditing.AuditEntity;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.pojo.SimpleEmployee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "schedule_summary", schema = "schedule")
@Getter @Setter @NoArgsConstructor @ToString
public class ScheduleSummary extends AuditEntity {
	@Id
    @SequenceGenerator(name="schedule_summary_sqe", sequenceName="schedule.schedule_summary_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="schedule_summary_sqe")
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    @ToString.Exclude

    private Employee employee;

    @Transient
    @ToString.Exclude
    @OrderBy("fullName asc")
    private SimpleEmployee simpleEmployee;

    @OneToMany
    @JoinTable(
            name = "schedule_mapping",
            schema = "schedule",
            joinColumns = {@JoinColumn(name = "schedule_summary_id")},
            inverseJoinColumns = {@JoinColumn(name = "schedule_record_id")}
            )
    @OrderBy("workDate asc, types asc")
    private List<ScheduleRecord> scheduleRecords;

    @NotNull
    private LocalDate scheduleDate;

	public ScheduleSummary(Employee employee, String scheduleDate) {
		this.employee = employee;
		this.simpleEmployee = new SimpleEmployee(employee);
		this.scheduleDate = LocalDate.parse(scheduleDate);
	}
    
    
}
