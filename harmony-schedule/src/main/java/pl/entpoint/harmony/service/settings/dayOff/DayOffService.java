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
	
	DayOff getDayOff(Date date);
	List<DayOff> getDayOffBetweenDats(Date start, Date end);
	void create(DayOff dayOff);
	void delete(Date date);
}
