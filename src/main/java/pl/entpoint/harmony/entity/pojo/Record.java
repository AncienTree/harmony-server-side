package pl.entpoint.harmony.entity.pojo;

import java.io.Serializable;
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
public class Record implements Serializable {
	private static final long serialVersionUID = -2695706741763156959L;

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
