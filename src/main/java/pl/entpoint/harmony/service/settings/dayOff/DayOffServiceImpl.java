package pl.entpoint.harmony.service.settings.dayOff;

import java.time.LocalDate;
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

	final DayOffRepository dayOffRepository;
	
	@Autowired
	public DayOffServiceImpl(DayOffRepository dayOffRepository) {
		this.dayOffRepository = dayOffRepository;
	}

	@Override
	public List<DayOff> getAllDayOff() {
		return dayOffRepository.findAll();
	}

	@Override
	public List<DayOff> getDayOffBetweenDats(LocalDate start, LocalDate end) {
		return dayOffRepository.findByDateBetween(start, end);
	}

	@Override
	public DayOff getDayOff(LocalDate date) {
		return dayOffRepository.findByDate(date);
	}

	@Override
	public void create(DayOff dayOff) {
		dayOffRepository.save(dayOff);
	}

	@Override
	public void delete(Long id) {
		DayOff day = dayOffRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Nie znaleziono dnia wolnego od pracy."));
		dayOffRepository.delete(day);
	}
}
