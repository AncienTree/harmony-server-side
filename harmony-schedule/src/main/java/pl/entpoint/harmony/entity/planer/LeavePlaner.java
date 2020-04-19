package pl.entpoint.harmony.entity.planer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "Leave_planer", schema = "planer")
@Getter
@Setter
@NoArgsConstructor
public class LeavePlaner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "planer_date", nullable = false)
    private Date planerDate;

    @Column(nullable = false)
    private boolean active;
}
