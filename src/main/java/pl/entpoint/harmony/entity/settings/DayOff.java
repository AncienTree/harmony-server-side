package pl.entpoint.harmony.entity.settings;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;

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

    @Column(unique = true)
    @NotNull
    private LocalDate date;

    private String info;

	public DayOff(Long id, @NotNull LocalDate date, String info) {
		if(id < 1) {
			this.id = 0L;
		} else {
			this.id = id;
		}		
		this.date = date;
		this.info = info;
	}   
}
