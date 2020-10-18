package pl.entpoint.harmony.entity.planer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "leave_planer_summary", schema = "planer")
@Getter @Setter @NoArgsConstructor
public class LeavePlanerSummary extends AuditEntity {
	@Id
    @SequenceGenerator(name="leave_planer_summary_sqe", sequenceName="planer.leave_planer_summary_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="leave_planer_summary_sqe")
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany
    @JoinTable(name = "leave_planer_mapping",
            schema = "planer",
            joinColumns = @JoinColumn(name = "leave_planer_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_record_id"))
    private List<ScheduleRecord> scheduleRecords;

    @NotNull
    private LocalDate year;

    @Column(name = "accepted_by")
    @NotNull
    private String acceptedBy;

}
