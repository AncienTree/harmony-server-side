package pl.entpoint.harmony.entity.availability;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "availability_summary", schema = "availability")
@Getter
@Setter
@NoArgsConstructor
public class AvailabilitySummary implements Serializable {
	private static final long serialVersionUID = -41960536005894529L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Date availabilityDate;

    @Column(name = "accepted_by", nullable = false)
    private String acceptedBy;
}
