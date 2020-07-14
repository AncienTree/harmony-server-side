package pl.entpoint.harmony.service.settings.monthHours;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.settings.MonthHours;

import java.util.Map;

/**
 * @author Mateusz Dąbek
 * @created 20.05.2020
 */

@Service
public class MonthHoursServiceImpl implements MonthHoursService {
    final MonthHoursRepository monthHoursRepository;

    @Autowired
    public MonthHoursServiceImpl(MonthHoursRepository monthHoursRepository) {
        this.monthHoursRepository = monthHoursRepository;
    }   

    @Override
	public MonthHours getMonthHoursByYear(String year) {
		return monthHoursRepository.findByYear(year);
	}
    
	@Override
    public void create(MonthHours monthHours) {
        monthHoursRepository.save(monthHours);
    }

    @Override
    public void delete(MonthHours monthHours) {
        monthHoursRepository.delete(monthHours);
    }

    @Override
    public void change(Map<String, String> monthHours) {
        Integer id = Integer.parseInt(monthHours.get("id"));
        MonthHours hours = monthHoursRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Nie znaleziono podanego miesiąca."));
        hours.setJanuary(Integer.parseInt(monthHours.get("january")));
        hours.setFebruary(Integer.parseInt(monthHours.get("february")));
        hours.setMarch(Integer.parseInt(monthHours.get("march")));
        hours.setApril(Integer.parseInt(monthHours.get("april")));
        hours.setMay(Integer.parseInt(monthHours.get("may")));
        hours.setJune(Integer.parseInt(monthHours.get("june")));
        hours.setJuly(Integer.parseInt(monthHours.get("july")));
        hours.setAugust(Integer.parseInt(monthHours.get("august")));
        hours.setSeptember(Integer.parseInt(monthHours.get("september")));
        hours.setOctober(Integer.parseInt(monthHours.get("october")));
        hours.setNovember(Integer.parseInt(monthHours.get("november")));
        hours.setDecember(Integer.parseInt(monthHours.get("december")));        

        monthHoursRepository.save(hours);
    }
}
