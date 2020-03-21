package pl.entpoint.harmony.entity.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import pl.entpoint.harmony.entity.user.enums.Roles;
import pl.entpoint.harmony.entity.employee.Employee;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 11/11/2019
 */


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String login;

    @JsonIgnore
    @Column(nullable = false, length = 68)
    private String password;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false)
    @JsonIgnore
    private Date created;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
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
        this.created = new Date(new java.util.Date().getTime());
        log.info("Stworzono nowego uzytkownika: " + login);
    }
}
