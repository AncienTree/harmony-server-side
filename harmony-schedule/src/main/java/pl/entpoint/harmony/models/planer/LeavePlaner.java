package pl.entpoint.harmony.models.planer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "Leave_planer")
@Getter
@Setter
@NoArgsConstructor
public class LeavePlaner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "planer_date", nullable = false)
    private LocalDate planerDate;

    @Column(nullable = false)
    private boolean active;
}
