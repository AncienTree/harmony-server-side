package pl.entpoint.harmony.entity.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Mateusz Dąbek
 * @created 14/11/2019
 */

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "employee_info", schema = "employee")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeInfo implements Serializable {
	private static final long serialVersionUID = -4010973083833894767L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean agreement;

    private boolean ppk;

    private boolean headphones;

    @Column(length = 25)
    private String locker;

    @Column(name = "id_card", length = 25)
    private String idCard;

    @Column(name = "parking_card", length = 25)
    private String parkingCard;

    private String info1;

    private String info2;

    private String info3;

    private String info4;

}
