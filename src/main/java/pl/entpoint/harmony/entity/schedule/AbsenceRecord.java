package pl.entpoint.harmony.entity.schedule;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;
import pl.entpoint.harmony.entity.employee.Employee;

/**
 * @author Mateusz Dąbek
 * @created 21 wrz 2020
 * 
 */


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "absence_record", schema = "schedule")
@Getter @Setter @NoArgsConstructor
public class AbsenceRecord extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = 3111132921570984L;

	@Id
	@SequenceGenerator(name="absence_record_sqe", sequenceName="schedule.absence_record_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="absence_record_sqe")
	private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @NotNull
    private Employee employee;

    @Column(name = "work_date")
    @NotNull
    private LocalDate workDate;

    @Column(name ="accepted_by")
    private String acceptedBy;
    
    private boolean visible = true;
}