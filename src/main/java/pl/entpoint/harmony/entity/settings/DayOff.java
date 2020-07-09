package pl.entpoint.harmony.entity.settings;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
public class DayOff extends AuditEntity implements Serializable {

	private static final long serialVersionUID = -5061684180186561809L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    private LocalDate date;

    private String info;

	public DayOff(Long id, @NotNull LocalDate date, String info) {
		if(id < 1 || id == null) {
			this.id = 0L;
		} else {
			this.id = id;
		}		
		this.date = date;
		this.info = info;
	}   
}
