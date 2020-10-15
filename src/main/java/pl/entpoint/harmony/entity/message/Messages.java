package pl.entpoint.harmony.entity.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;
import pl.entpoint.harmony.entity.employee.Employee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "messages", schema = "messages")
@Getter @Setter @NoArgsConstructor
public class Messages extends AuditEntity {
	@Id
    @SequenceGenerator(name="messages_sqe", sequenceName="messages.messages_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="messages_sqe")
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id")
    @NotNull
    private Employee employee;

    @NotNull
    private String text;

    private LocalDate expired;
}
