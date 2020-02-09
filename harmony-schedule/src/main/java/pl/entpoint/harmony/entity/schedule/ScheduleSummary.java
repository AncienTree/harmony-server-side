package pl.entpoint.harmony.entity.schedule;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.model.SimpleEmployee;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "schedule_summary")
@Getter
@Setter
@NoArgsConstructor
public class ScheduleSummary implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;

    @Transient
    private SimpleEmployee simpleEmployee;

    @OneToMany
    @JoinTable(
            name = "schedule_mapping",
            joinColumns = {@JoinColumn(name = "schedule_summary_id")},
            inverseJoinColumns = {@JoinColumn(name = "schedule_record_id")}
            )
    private List<ScheduleRecord> scheduleRecords;

    private LocalDate scheduleDate;
}