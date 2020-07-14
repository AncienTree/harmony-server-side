package pl.entpoint.harmony.service.settings.monthHours;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.entpoint.harmony.entity.settings.MonthHours;

/**
 * @author Mateusz Dąbek
 * @created 20.05.2020
 */

@Repository
public interface MonthHoursRepository extends JpaRepository<MonthHours, Integer> {
    MonthHours findByYear(String year);
}
