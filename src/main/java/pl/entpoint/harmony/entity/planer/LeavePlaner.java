package pl.entpoint.harmony.entity.planer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "leave_planer", schema = "planer")
@Getter @Setter @NoArgsConstructor
public class LeavePlaner extends AuditEntity {
	@Id
    @SequenceGenerator(name="leave_planer_sqe", sequenceName="planer.leave_planer_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="leave_planer_sqe")
    private Long id;

    @Column(name = "planer_date")
    @NotNull
    private LocalDate planerDate;

    @NotNull
    private boolean active;
}
