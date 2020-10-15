package pl.entpoint.harmony.entity.availability;

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
@Table(name = "availability", schema = "availability")
@Getter @Setter @NoArgsConstructor
public class Availability extends AuditEntity {
	@Id
    @SequenceGenerator(name="availability_sqe", sequenceName="availability.availability_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="availability_sqe")
    private Long id;

    @Column(name = "availability_date")
    @NotNull
    private LocalDate availabilityDate;

    @NotNull
    private boolean active;
}
