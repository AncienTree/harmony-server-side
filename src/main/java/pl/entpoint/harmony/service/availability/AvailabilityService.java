package pl.entpoint.harmony.service.availability;

import pl.entpoint.harmony.entity.availability.Availability;
import pl.entpoint.harmony.entity.pojo.AvailabilityHelper;
import pl.entpoint.harmony.entity.pojo.controller.DayOffPojo;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 14.12.2020
 */

public interface AvailabilityService {
    Availability getAvailability(LocalDate date);
    AvailabilityHelper checkAvailability(LocalDate date);
    List<Availability> getAllActive();
    void create(LocalDate date);
    void closeAvailability(LocalDate date);
}
