package pl.entpoint.harmony.entity.pojo;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 18.10.2020
 */

@Data
public class SchedulePojo {
    private Long id;
    private LocalDate scheduleDate;
    private boolean active;
    private boolean visible;
}
