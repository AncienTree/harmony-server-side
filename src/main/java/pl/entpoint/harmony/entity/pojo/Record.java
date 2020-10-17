package pl.entpoint.harmony.entity.pojo;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.entpoint.harmony.entity.schedule.enums.ScheduleStatus;
import pl.entpoint.harmony.entity.schedule.enums.ScheduleType;

/**
 * @author Mateusz Dąbek
 * @created 31 sie 2020
 * 
 */

@Getter @Setter 
@ToString
public class Record  {
	private Long id;
	@NotNull
    private Long employee;
	@NotNull
    private String workDate;
	@NotNull
    private String startWork;
	@NotNull
    private String endWork;
	@NotNull
    private ScheduleStatus status;
	@NotNull
    private ScheduleType types;
}
