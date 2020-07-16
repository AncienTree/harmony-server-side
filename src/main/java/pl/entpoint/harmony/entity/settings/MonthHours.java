package pl.entpoint.harmony.entity.settings;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Mateusz Dąbek
 * @created 18/01/2020
 */

@Entity
@Table(name = "month_hours", schema = "settings")
@Getter @Setter @NoArgsConstructor
public class MonthHours extends AuditEntity implements Serializable {
	private static final long serialVersionUID = -3553573220315149028L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @NotNull
    @Size(max = 4)
    private String year;

    @NotNull
    private int january;
    
    @NotNull
    private int february;
    
    @NotNull
    private int march;
    
    @NotNull
    private int april;
    
    @NotNull
    private int may;
    
    @NotNull
    private int june;
    
    @NotNull
    private int july;
    
    @NotNull
    private int august;
    
    @NotNull
    private int september;
    
    @NotNull
    private int october;
    
    @NotNull
    private int november;
    
    @NotNull
    private int december;

    public int getRbh(int month){
        switch (month){
            case 1: return getJanuary();
            case 2: return getFebruary();
            case 3: return getMarch();
            case 4: return getApril();
            case 5: return getMay();
            case 6: return getJune();
            case 7: return getJuly();
            case 8: return getAugust();
            case 9: return getSeptember();
            case 10: return getOctober();
            case 11: return getNovember();
            case 12: return getDecember();
            default: return 0;
        }
    }
}
