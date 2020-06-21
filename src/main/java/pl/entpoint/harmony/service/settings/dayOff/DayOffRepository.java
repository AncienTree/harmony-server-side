package pl.entpoint.harmony.service.settings.dayOff;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.entpoint.harmony.entity.settings.DayOff;

/**
 * @author Mateusz Dąbek
 * @created 19 maj 2020
 * 
 */

@Repository
public interface DayOffRepository extends JpaRepository<DayOff, Long> {
	DayOff findByDate(Date date);
	List<DayOff> findByDateBetween(Date start, Date end);
}
