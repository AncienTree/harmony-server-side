package pl.entpoint.harmony.entity.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mateusz Dąbek
 * @created 06.09.2020
 */

@Getter @Setter
public class Stats {
    private int currentMonthWorkedHours;
    private int currentMonthHours;
    private int leaves;
    private int currentMonthLeaves;
    private int absences;
    private int currentMonthAbsences;

}
