package pl.entpoint.harmony.entity.availability;

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
@Table(name = "availability", schema = "availability")
@Getter @Setter @NoArgsConstructor
public class Availability extends AuditEntity implements Serializable{
	private static final long serialVersionUID = 1136414836525890890L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "availability_date")
    @NotNull
    private Date availabilityDate;

    @NotNull
    private boolean active;
}
