package pl.entpoint.harmony.entity.pojo.controller;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.entpoint.harmony.entity.schedule.enums.AbsenceStatus;

/**
 * @author Mateusz Dąbek
 * @created 24 wrz 2020
 * 
 */

@Data @NoArgsConstructor
public class AbsencePojo {
	private Long id;
    private Long employee;
    private LocalDate workDate;
    private String acceptedBy;
    private String text;
    private AbsenceStatus status;
}
