package pl.entpoint.harmony.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;
import pl.entpoint.harmony.entity.schedule.enums.ScheduleType;

import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 30.11.2020
 */

@Getter @Setter @AllArgsConstructor
public class SimpleSchedule {
    private SimpleEmployee simpleEmployee;
    private ScheduleType type;
    private List<ScheduleRecord> scheduleRecord;
}
