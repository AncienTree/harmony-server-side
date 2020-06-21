package pl.entpoint.harmony.service.settings.monthHours;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.entpoint.harmony.entity.settings.MonthHours;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 20.05.2020
 */

@Repository
public interface MonthHoursRepository extends JpaRepository<MonthHours, Long> {
    List<MonthHours> findAllByDateBetween(LocalDate start, LocalDate end);
    MonthHours findByDate(LocalDate date);
}
