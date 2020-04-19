package pl.entpoint.harmony.entity.availability;

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
@Table(name = "availability", schema = "availability")
@Getter
@Setter
@NoArgsConstructor
public class Availability implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "availability_date", nullable = false)
    private Date availabilityDate;

    @Column(nullable = false)
    private boolean active;
}
