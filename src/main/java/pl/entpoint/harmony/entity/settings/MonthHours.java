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
}
