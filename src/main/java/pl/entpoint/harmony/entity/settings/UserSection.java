package pl.entpoint.harmony.entity.settings;

import java.time.LocalDate;

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
public class UserSection extends AuditEntity {
	@Id
    @SequenceGenerator(name="user_section_sqe", sequenceName="settings.user_section_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_section_sqe")
    private Long id;

    @Column(unique = true)
    @NotNull
    @Size(max = 40)
    private String name;

    @NotNull
    private LocalDate expired;

    @Column(name = "lider")
    @NotNull
    private String lider;
}
