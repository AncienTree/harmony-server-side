package pl.entpoint.harmony.service.settings.dayOff;

import java.sql.Date;
import java.util.List;

import pl.entpoint.harmony.entity.settings.DayOff;

/**
 * @author Mateusz Dąbek
 * @created 19 maj 2020
 * 
 */

public interface DayOffService {

	List<DayOff> getAllDayOff();
	List<DayOff> getDayOffBetweenDats(Date start, Date end);
	DayOff getDayOff(Date date);
	void create(DayOff dayOff);
	void delete(Long id);
}
