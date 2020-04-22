package pl.entpoint.harmony.entity.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "schedule", schema = "schedule")
@Getter
@Setter
@NoArgsConstructor
public class Schedule implements Serializable {
	private static final long serialVersionUID = 1315074368792278980L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "schedule_date", nullable = false)
    private Date scheduleDate;

    @Column(nullable = false)
    private boolean active;
}
