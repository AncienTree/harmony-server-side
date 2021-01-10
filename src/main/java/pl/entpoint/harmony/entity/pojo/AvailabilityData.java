package pl.entpoint.harmony.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.entpoint.harmony.entity.settings.DayOff;

import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 15.01.2021
 */

@Getter @Setter @AllArgsConstructor
public class AvailabilityData {
    private List<DayOff> dayOffs;
    private int monthHours;
}
