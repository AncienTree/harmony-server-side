package pl.entpoint.harmony.service.settings.dayOff;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.settings.DayOff;

/**
 * @author Mateusz Dąbek
 * @created 19 maj 2020
 * 
 */

@Service
@AllArgsConstructor
public class DayOffServiceImpl implements DayOffService {
	private final DayOffRepository dayOffRepository;

	@Override
	public List<DayOff> getDayOffByYear(String year) {
		LocalDate start = LocalDate.of(Integer.parseInt(year), 1, 1);
		LocalDate end = LocalDate.of(Integer.parseInt(year), 12, 31);
		
		return dayOffRepository.findByDateBetween(start, end);
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
	public void update(DayOff dayOff) {
		Long id = dayOff.getId();
		Optional<DayOff> optional = Optional.ofNullable(dayOffRepository.findById(id))
					.orElseThrow(() -> new IllegalArgumentException("Nie znaleziono dnia wolnego o takim ID"));
		DayOff tempDayOff = null;
		
		if(optional.isPresent()) {
			tempDayOff = optional.get();
		}
		assert tempDayOff != null;
		tempDayOff.setDate(dayOff.getDate());
		tempDayOff.setInfo(dayOff.getInfo());		
		
		create(tempDayOff);
	}
	
	@Override
	public void delete(Long id) {
		DayOff day = dayOffRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Nie znaleziono dnia wolnego od pracy."));
		dayOffRepository.delete(day);
	}
}
