package pl.entpoint.harmony.service.settings.dayOff;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.settings.DayOff;

/**
 * @author Mateusz Dąbek
 * @created 19 maj 2020
 * 
 */

@Service
public class DayOffServiceImpl implements DayOffService {

	DayOffRepository dayOffRepository;
	
	@Autowired
	public DayOffServiceImpl(DayOffRepository dayOffRepository) {
		super();
		this.dayOffRepository = dayOffRepository;
	}

	@Override
	public DayOff getDayOff(Date date) {
		return dayOffRepository.findByDate(date);
	}

	@Override
	public List<DayOff> getDayOffBetweenDats(Date start, Date end) {
		return dayOffRepository.findByDateBetween(start, end);
	}

	@Override
	public void create(DayOff dayOff) {
		dayOffRepository.save(dayOff);
	}

	@Override
	public void delete(Date date) {
		DayOff day = dayOffRepository.findByDate(date);
		dayOffRepository.delete(day);
	}
}
