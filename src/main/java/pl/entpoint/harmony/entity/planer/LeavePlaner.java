package pl.entpoint.harmony.entity.planer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "Leave_planer", schema = "planer")
@Getter @Setter @NoArgsConstructor
public class LeavePlaner extends AuditEntity implements Serializable {
	private static final long serialVersionUID = -6919831420560838186L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "planer_date")
    @NotNull
    private Date planerDate;

    @NotNull
    private boolean active;
}
