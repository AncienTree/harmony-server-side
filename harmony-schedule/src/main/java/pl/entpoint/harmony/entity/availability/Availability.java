package pl.entpoint.harmony.entity.availability;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "availability")
@Getter
@Setter
@NoArgsConstructor
public class Availability implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "availability_date", nullable = false)
    private LocalDate availabilityDate;

    @Column(nullable = false)
    private boolean active;
}
