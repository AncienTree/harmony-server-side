package pl.entpoint.harmony.entity.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;
import pl.entpoint.harmony.entity.settings.DayOff;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "schedule", schema = "schedule")
@Getter @Setter @NoArgsConstructor
public class Schedule extends AuditEntity implements Comparable<Schedule> {
	@Id
    @SequenceGenerator(name="schedule_sqe", sequenceName="schedule.schedule_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="schedule_sqe")
    private Long id;

    @Column(name = "schedule_date")
    @NotNull
    private LocalDate scheduleDate;

    @NotNull
    private boolean active;
    
    private boolean visible;
    
    @Transient
    private List<DayOff> dayOffs;
    
    @Transient
    private int rbh;

    @Override
    public int compareTo(Schedule schedule) {
        return getScheduleDate().compareTo(schedule.getScheduleDate());
    }
}
