package pl.entpoint.harmony.entity.settings;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;
import pl.entpoint.harmony.entity.pojo.controller.DayOffPojo;

/**
 * @author Mateusz Dąbek
 * @created 19 maj 2020
 * 
 */

@Entity
@Table(name = "dayoff", schema = "settings")
@Getter @Setter @NoArgsConstructor
public class DayOff extends AuditEntity {
	@Id
	@SequenceGenerator(name="dayoff_sqe", sequenceName="settings.dayoff_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="dayoff_sqe")
    private Long id;

    @Column(unique = true, name = "date_off")
    @NotNull
    private LocalDate date;

    private String info;

	public DayOff(DayOffPojo dayOff) {
		this.date = dayOff.getDate();
		this.info = dayOff.getInfo();
	}
}
