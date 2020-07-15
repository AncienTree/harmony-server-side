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
    @Size(max = 4)
    private int january;
    
    @NotNull
    @Size(max = 4)
    private int february;
    
    @NotNull
    @Size(max = 4)
    private int march;
    
    @NotNull
    @Size(max = 4)
    private int april;
    
    @NotNull
    @Size(max = 4)
    private int may;
    
    @NotNull
    @Size(max = 4)
    private int june;
    
    @NotNull
    @Size(max = 4)
    private int july;
    
    @NotNull
    @Size(max = 4)
    private int august;
    
    @NotNull
    @Size(max = 4)
    private int september;
    
    @NotNull
    @Size(max = 4)
    private int october;
    
    @NotNull
    @Size(max = 4)
    private int november;
    
    @NotNull
    @Size(max = 4)
    private int december;
}
