package pl.entpoint.harmony.entity.settings;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.entity.employee.Employee;

/**
 * @author Mateusz Dąbek
 * @created 18/11/2019
 */

@Entity
@Table(name = "user_section", schema = "settings")
@Getter
@Setter
@NoArgsConstructor
public class UserSection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 40)
    private String name;

    @Column(nullable = false)
    private Date expired;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee lider;
}
