package pl.entpoint.harmony.entity.settings;

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
@Table(name = "user_line", schema = "settings")
@Getter @Setter @NoArgsConstructor
public class UserLine extends AuditEntity {
	@Id
    @SequenceGenerator(name="user_line_sqe", sequenceName="settings.user_line_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_line_sqe")
    private Long id;

    @Column(unique = true)
    @NotNull
    @Size(max = 20)
    private String name;
}
