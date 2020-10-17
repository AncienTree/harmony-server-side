package pl.entpoint.harmony.service.settings.dayOff;

import java.time.LocalDate;
import java.util.List;

import pl.entpoint.harmony.entity.pojo.controller.DayOffPojo;
import pl.entpoint.harmony.entity.settings.DayOff;

/**
 * @author Mateusz Dąbek
 * @created 19 maj 2020
 * 
 */

public interface DayOffService {
	DayOff getDayOff(LocalDate date);
	List<DayOff> getDayOffByYear(String year);
	List<DayOff> getDayOffBetweenDats(LocalDate start, LocalDate end);
	void create(DayOff dayOff);
	void update(DayOffPojo dayOff);
	void delete(Long id);
}
