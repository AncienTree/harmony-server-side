package pl.entpoint.harmony.entity.settings;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "contract_type", schema = "settings")
@Getter @Setter @NoArgsConstructor
public class ContractType extends AuditEntity implements Serializable {
	private static final long serialVersionUID = 118698729771543703L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    @Size(max = 50)
    private String name;

	public ContractType(Long id, String name) {
		
		if(id < 1 || id == null) {
			this.id = 0L;
		} else {
			this.id = id;
		}		
		this.name = name;
	}    
}
