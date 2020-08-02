package pl.entpoint.harmony.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;
import pl.entpoint.harmony.entity.schedule.enums.ScheduleType;

import java.io.Serializable;
import java.util.List;

/**
 * @author Mateusz Dąbek
 * @created 02.08.2020
 */

@Getter @Setter @AllArgsConstructor
public class Presence implements Serializable {
    private static final long serialVersionUID = 3110577428665471954L;

    ScheduleType scheduleType;
    List<ScheduleRecord> recordList;

}
