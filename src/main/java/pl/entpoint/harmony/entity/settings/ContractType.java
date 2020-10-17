package pl.entpoint.harmony.entity.settings;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;
import pl.entpoint.harmony.entity.pojo.controller.ContractPojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "contract_type", schema = "settings")
@Getter @Setter @NoArgsConstructor
public class ContractType extends AuditEntity {
	@Id
	@SequenceGenerator(name="contract_type_sqe", sequenceName="settings.contract_type_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="contract_type_sqe")
    private Long id;

    @Column(unique = true)
    @NotNull
    @Size(max = 50)
    private String name;

	public ContractType(ContractPojo contractPojo) {
		this.name = contractPojo.getName();
	}    
}
