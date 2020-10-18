package pl.entpoint.harmony.entity.pojo;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mateusz Dąbek
 * @created 3 sie 2020
 * 
 */

@Getter @Setter @NoArgsConstructor
public class EmployeeScheduleList {
	private LocalDate scheduleDate;
	private List<SimpleEmployee> employees;
}
