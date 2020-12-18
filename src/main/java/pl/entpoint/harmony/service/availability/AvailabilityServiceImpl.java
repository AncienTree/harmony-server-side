package pl.entpoint.harmony.service.availability;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.availability.Availability;
import pl.entpoint.harmony.entity.pojo.AvailabilityHelper;
import pl.entpoint.harmony.entity.pojo.controller.DayOffPojo;
import pl.entpoint.harmony.entity.settings.DayOff;
import pl.entpoint.harmony.service.employee.EmployeeService;
import pl.entpoint.harmony.service.settings.dayOff.DayOffService;
import pl.entpoint.harmony.service.settings.monthHours.MonthHoursService;
import pl.entpoint.harmony.util.exception.availability.AvailabilityNotFoundException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Mateusz Dąbek
 * @created 14.12.2020
 */

@Service
@AllArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {

    final AvailabilityRepository availabilityRepository;
    final DayOffService dayOffService;
    final MonthHoursService hoursService;
    final EmployeeService employeeService;

    @Override
    public Availability getAvailability(LocalDate date) {
        return Optional.ofNullable(availabilityRepository.findByAvailabilityDate(date))
                .orElseThrow(() -> new AvailabilityNotFoundException(date));
    }

    @Override
    public AvailabilityHelper checkAvailability(LocalDate date) {
        Optional<Availability> optionalAvailability = Optional.ofNullable(availabilityRepository.findByAvailabilityDate(date));
        AvailabilityHelper helper = new AvailabilityHelper();

        if (optionalAvailability.isPresent()) {
            helper.setActive(true);
        } else {
            helper.setActive(false);
            helper.setDayOffs(dayOffService.getDayOffBetweenDats(date,
                    LocalDate.of(date.getYear(), date.getMonth(),date.lengthOfMonth() )));
            helper.setRbh(hoursService.checkMonthHoursByMonth(date));
            helper.setNumbersOfEmployees(employeeService.numberOfEmployee());
        }
        return helper;
    }

    @Override
    @Transactional
    public void create(LocalDate date) {
        Optional<Availability> optional = Optional.ofNullable(availabilityRepository
                    .findByAvailabilityDate(date));

        if (optional.isPresent()){
            throw new IllegalArgumentException("Dyspozycyjność o podanej dacie istnieje już w bazie");
        } else {
            Availability availability = new Availability();
            availability.setAvailabilityDate(date);
            availability.setActive(true);
            availabilityRepository.save(availability);
        }
    }

    @Override
    public void closeAvailability(LocalDate date) {
        Availability availability = Optional.ofNullable(availabilityRepository
                .findByAvailabilityDate(date))
                .orElseThrow(() -> new AvailabilityNotFoundException(date));

        availability.setActive(false);
        availabilityRepository.save(availability);
    }
}

