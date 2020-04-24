package pl.entpoint.harmony.entity.settings;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "month_hours", schema = "settings")
@Getter
@Setter
@NoArgsConstructor
public class MonthHours extends AuditEntity implements Serializable {
	private static final long serialVersionUID = -3553573220315549028L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false, length = 4)
    private int rbh;
}
