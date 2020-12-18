package pl.entpoint.harmony.entity.pojo.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 14.12.2020
 */

@Data @NoArgsConstructor
public class AvailabilityCreation {
    private LocalDate date;
    private List<DayOffPojo> daysOff;
}
