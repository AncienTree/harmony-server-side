package pl.entpoint.harmony.entity.planer;

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
@Table(name = "leave_planer_summary", schema = "planer")
@Getter
@Setter
@NoArgsConstructor
public class LeavePlanerSummary implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(nullable = false)
    private Date year;

    @Column(name = "accepted_by", nullable = false)
    private String acceptedBy;

}
