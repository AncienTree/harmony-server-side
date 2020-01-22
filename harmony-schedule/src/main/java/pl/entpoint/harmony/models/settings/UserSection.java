package pl.entpoint.harmony.models.settings;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.models.employee.Employee;

/**
 * @author Mateusz Dąbek
 * @created 18/11/2019
 */

@Entity
@Table(name = "sett_section")
@Getter
@Setter
@NoArgsConstructor
public class UserSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 40)
    private String name;

    @Column(nullable = false)
    private LocalDate expired;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee lider;
}
