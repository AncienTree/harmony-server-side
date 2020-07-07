package pl.entpoint.harmony.service.settings.monthHours;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.settings.MonthHours;

import java.time.LocalDate;
import java.util.List;
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
    public List<MonthHours> getMonthHoursOnYear(int year) {
        LocalDate start = LocalDate.of(year, 1,1);
        LocalDate end = LocalDate.of(year, 12,1);

        return monthHoursRepository.findAllByDateBetween(start, end);
    }

    @Override
    public MonthHours getMonthHoursByDate(LocalDate date) {
        return monthHoursRepository.findByDate(date);
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
        Long id = Long.parseLong(monthHours.get("id"));
        MonthHours hours = monthHoursRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Nie znaleziono podanego miesiąca."));

        hours.setRbh(Integer.parseInt(monthHours.get("rbh")));

        monthHoursRepository.save(hours);
    }
}
