package pl.entpoint.harmony.service.settings.dayOff;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.pojo.controller.DayOffPojo;
import pl.entpoint.harmony.entity.settings.DayOff;
import pl.entpoint.harmony.util.exception.setting.DayOffNotFoundException;

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
	public DayOff getDayOff(LocalDate date) {
		return dayOffRepository.findByDate(date);
	}

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
	public void create(DayOff dayOff) {
		dayOffRepository.save(dayOff);
	}
		
	@Override
	public void update(DayOffPojo dayOff) {
		DayOff day = dayOffRepository.findById(dayOff.getId())
				.orElseThrow(DayOffNotFoundException::new);

		day.setDate(dayOff.getDate());
		day.setInfo(dayOff.getInfo());
		
		create(day);
	}
	
	@Override
	public void delete(Long id) {
		DayOff day = dayOffRepository.findById(id)
				.orElseThrow(DayOffNotFoundException::new);
		dayOffRepository.delete(day);
	}
}
