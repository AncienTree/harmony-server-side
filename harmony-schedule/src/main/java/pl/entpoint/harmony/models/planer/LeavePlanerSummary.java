package pl.entpoint.harmony.models.planer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.models.employee.Employee;
import pl.entpoint.harmony.models.schedule.ScheduleRecord;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "leave_planer_summary")
@Getter
@Setter
@NoArgsConstructor
public class LeavePlanerSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany
    @JoinTable(name = "leave_planer_mapping",
            joinColumns = @JoinColumn(name = "leave_planer_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_record_id"))
    private List<ScheduleRecord> scheduleRecords;

    @Column(nullable = false)
    private LocalDate year;

    @Column(name = "accepted_by", nullable = false)
    private String acceptedBy;

}
