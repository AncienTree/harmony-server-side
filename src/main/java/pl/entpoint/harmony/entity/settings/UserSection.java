package pl.entpoint.harmony.entity.settings;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;

/**
 * @author Mateusz Dąbek
 * @created 18/11/2019
 */

@Entity
@Table(name = "user_section", schema = "settings")
@Getter @Setter @NoArgsConstructor
public class UserSection extends AuditEntity implements Serializable {
	private static final long serialVersionUID = 3600600240967351713L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    @Size(max = 40)
    private String name;

    @NotNull
    private Date expired;

    @Column(name = "lider")
    @NotNull
    private String lider;
}
