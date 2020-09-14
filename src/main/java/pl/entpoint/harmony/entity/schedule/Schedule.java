package pl.entpoint.harmony.entity.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;
import pl.entpoint.harmony.entity.settings.DayOff;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "schedule", schema = "schedule")
@Getter @Setter @NoArgsConstructor
public class Schedule extends AuditEntity implements Serializable, Comparable<Schedule> {
	private static final long serialVersionUID = 1315074368792278981L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Schedule(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
        this.active = true;
        this.visible = true;
    }

    @Override
    public int compareTo(Schedule schedule) {
        return getScheduleDate().compareTo(schedule.getScheduleDate());
    }
}
