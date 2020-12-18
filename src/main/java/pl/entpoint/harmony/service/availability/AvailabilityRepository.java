package pl.entpoint.harmony.service.availability;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.entpoint.harmony.entity.availability.Availability;

import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 14.12.2020
 */

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    Availability findByAvailabilityDate(LocalDate date);
}
