package pl.entpoint.harmony.entity.settings;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "contract_type", schema = "settings")
@Getter
@Setter
@NoArgsConstructor
public class ContractType extends AuditEntity implements Serializable {
	private static final long serialVersionUID = 118698729771543703L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;
}
