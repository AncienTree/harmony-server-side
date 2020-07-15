package pl.entpoint.harmony.service.settings.dayOff;

import java.time.LocalDate;
import java.util.List;

import pl.entpoint.harmony.entity.settings.DayOff;

/**
 * @author Mateusz Dąbek
 * @created 19 maj 2020
 * 
 */

public interface DayOffService {

	List<DayOff> getDayOffByYear(String year);
	List<DayOff> getDayOffBetweenDats(LocalDate start, LocalDate end);
	DayOff getDayOff(LocalDate date);
	void create(DayOff dayOff);
	void update(DayOff dayOff);
	void delete(Long id);
}
