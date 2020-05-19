package pl.entpoint.harmony.entity.settings;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;

/**
 * @author Mateusz Dąbek
 * @created 19 maj 2020
 * 
 */

@Entity
@Table(name = "dayoff", schema = "settings")
@Getter @Setter @NoArgsConstructor
public class DayOff extends AuditEntity implements Serializable {

	private static final long serialVersionUID = -5061684180186561809L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Date date;

    private String info;
}