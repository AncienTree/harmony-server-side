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
@Table(name = "unit", schema = "settings")
@Getter
@Setter
@NoArgsConstructor
public class Unit extends AuditEntity implements Serializable {
	private static final long serialVersionUID = -6038584818439693221L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(name = "for_unit",nullable = false, length = 45)
    private String forUnit;

    @Column(name = "update_date")
    private Date update;
}
