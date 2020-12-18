package pl.entpoint.harmony.entity.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.entpoint.harmony.entity.settings.DayOff;

import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 14.12.2020
 */

@Data @NoArgsConstructor
public class AvailabilityHelper {
    private boolean active;
    private int rbh;
    private List<DayOff> dayOffs;
    private Long numbersOfEmployees;
}
