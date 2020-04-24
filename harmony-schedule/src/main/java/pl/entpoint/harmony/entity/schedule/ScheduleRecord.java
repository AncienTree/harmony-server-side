package pl.entpoint.harmony.entity.schedule;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.entpoint.harmony.auditing.AuditEntity;
import pl.entpoint.harmony.entity.schedule.enums.ScheduleStatus;
import pl.entpoint.harmony.entity.schedule.enums.ScheduleType;
import pl.entpoint.harmony.entity.employee.Employee;

/**
 * @author Mateusz Dąbek
 * @created 16/11/2019
 */


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "schedule_record", schema = "schedule")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ScheduleRecord extends AuditEntity implements Serializable {
	private static final long serialVersionUID = 392393332921570984L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ToString.Exclude
    private Employee employee;

    @Column(name = "work_date", nullable = false)
    private Date workDate;

    @Column(name = "start_work", nullable = false)
    private Time startWork;

    @Column(name = "end_work", nullable = false)
    private Time endWork;

    @Enumerated(EnumType.STRING)
    private ScheduleStatus status;

    @Enumerated(EnumType.STRING)
    private ScheduleType types;

    @Column(name = "update_date")
    private Date update;

}
