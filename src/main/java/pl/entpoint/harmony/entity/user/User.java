package pl.entpoint.harmony.entity.user;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import pl.entpoint.harmony.auditing.AuditEntity;
import pl.entpoint.harmony.entity.user.enums.Roles;
import pl.entpoint.harmony.entity.employee.Employee;

/**
 * @author Mateusz Dąbek
 * @created 11/11/2019
 */

@Entity
@Table(name = "users", schema = "users")
@Getter @Setter @NoArgsConstructor
@Slf4j
public class User extends AuditEntity {
	@Id
    @SequenceGenerator(name="users_sqe", sequenceName="users.users_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="users_sqe")
    private Long id;

    @Column(unique = true)
    @NotNull
    @Size(min = 1, max = 50)
    private String login;

    @JsonIgnore
    @NotNull
    @Size(min = 8, max = 68)
    private String password;

    @NotNull
    private boolean status;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Roles role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.status = true;
        this.role = Roles.ROLE_USER;
        log.info("Stworzono nowego uzytkownika: " + login);
    }
}
