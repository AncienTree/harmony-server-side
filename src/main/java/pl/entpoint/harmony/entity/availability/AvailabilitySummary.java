package pl.entpoint.harmony.entity.availability;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "availability_summary", schema = "availability")
@Getter @Setter @NoArgsConstructor
public class AvailabilitySummary extends AuditEntity {
	@Id
    @SequenceGenerator(name="availability_summary_sqe", sequenceName="availability.availability_summary_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="availability_summary_sqe")
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany
    @JoinTable(name = "availability_mapping",
            schema = "availability",
            joinColumns = @JoinColumn(name = "availability_summary_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_record_id"))
    private List<ScheduleRecord> scheduleRecords;

    @Column(name = "availability_date", nullable = false)
    private LocalDate availabilityDate;

    @Column(name = "accepted_by", nullable = false)
    private String acceptedBy;

    @Column(name = "accepted_date", nullable = false)
    private Instant acceptedDate;
}
