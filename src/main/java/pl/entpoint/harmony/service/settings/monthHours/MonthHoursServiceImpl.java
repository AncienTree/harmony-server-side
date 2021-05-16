package pl.entpoint.harmony.service.settings.monthHours;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.pojo.controller.MonthlyHoursPojo;
import pl.entpoint.harmony.entity.settings.MonthHours;
import pl.entpoint.harmony.util.exception.setting.MonthHoursNotFoundException;

import java.time.LocalDate;
import java.util.List;
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
    public MonthHours getByYear(String year) {
        return Optional.ofNullable(monthHoursRepository.findByYear(year))
                .orElseThrow(MonthHoursNotFoundException::new);
    }

    @Override
    public int checkMonthHours(LocalDate date) {
        String dateString = String.valueOf(date.getYear());
        Optional<MonthHours> monthHours = Optional.ofNullable(monthHoursRepository.findByYear(dateString));
        MonthHours hours;

        if (monthHours.isPresent()) {
            hours = monthHours.get();
        } else {
            return 0;
        }

        return hours.getRbh(date.getMonthValue());
    }

    @Override
    public int checkMonthHoursByMonth(LocalDate date) {
        String dateString = String.valueOf(date.getYear());
        Optional<MonthHours> optHours = Optional.ofNullable(monthHoursRepository.findByYear(dateString));
        MonthHours monthHours;

        if (optHours.isPresent()) {
            monthHours = optHours.get();
            return monthHours.getRbh(date.getMonthValue());
        } else {
            return 0;
        }
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
