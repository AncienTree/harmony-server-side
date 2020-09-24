package pl.entpoint.harmony.entity.pojo;

import java.time.LocalDate;

import lombok.Data;

/**
 * @author Mateusz Dąbek
 * @created 24 wrz 2020
 * 
 */

@Data
public class AbsencePojo {
	private Long id;
    private Long employee;
    private LocalDate workDate;
    private String acceptedBy;
    private boolean visible;
}
