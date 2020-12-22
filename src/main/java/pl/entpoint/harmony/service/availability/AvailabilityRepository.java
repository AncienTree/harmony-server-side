package pl.entpoint.harmony.service.availability;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.entpoint.harmony.entity.availability.Availability;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 14.12.2020
 */

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    Availability findByAvailabilityDate(LocalDate date);
    List<Availability> findByActive(boolean active);
}
