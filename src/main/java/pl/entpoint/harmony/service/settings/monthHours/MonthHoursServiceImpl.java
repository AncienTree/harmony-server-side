package pl.entpoint.harmony.service.settings.monthHours;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.pojo.controller.MonthlyHoursPojo;
import pl.entpoint.harmony.entity.settings.MonthHours;
import pl.entpoint.harmony.util.exception.setting.MonthHoursNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Mateusz Dąbek
 * @created 20.05.2020
 */

@Service
@AllArgsConstructor
public class MonthHoursServiceImpl implements MonthHoursService {
    private final MonthHoursRepository monthHoursRepository;

    @Override
	public List<MonthHours> getAll() {
		return monthHoursRepository.findAll();
	}

    @Override
    public int checkMonthHours(LocalDate date) {
        String dateString = String.valueOf(date.getYear());
        MonthHours monthHours = Optional.of(monthHoursRepository.findByYear(dateString))
                .orElseThrow(MonthHoursNotFoundException::new);

        return monthHours.getRbh(date.getMonthValue());
    }

    @Override
    public void create(MonthHours monthHours) {
        monthHoursRepository.save(monthHours);
    }

    @Override
    public void change(MonthlyHoursPojo monthHours) {
        MonthHours hours = monthHoursRepository.findById(monthHours.getId())
                .orElseThrow(MonthHoursNotFoundException::new);

        hours.setJanuary(monthHours.getJanuary());
        hours.setFebruary(monthHours.getFebruary());
        hours.setMarch(monthHours.getMarch());
        hours.setApril(monthHours.getApril());
        hours.setMay(monthHours.getMay());
        hours.setJune(monthHours.getJune());
        hours.setJuly(monthHours.getJuly());
        hours.setAugust(monthHours.getAugust());
        hours.setSeptember(monthHours.getSeptember());
        hours.setOctober(monthHours.getOctober());
        hours.setNovember(monthHours.getNovember());
        hours.setDecember(monthHours.getDecember());

        monthHoursRepository.save(hours);
    }

    @Override
    public void delete(int id) {
        MonthHours hours = monthHoursRepository.findById(id)
                .orElseThrow(MonthHoursNotFoundException::new);

        monthHoursRepository.delete(hours);
    }
}
