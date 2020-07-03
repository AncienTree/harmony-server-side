package pl.entpoint.harmony.entity.employee;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.auditing.AuditEntity;

/**
 * @author Mateusz Dąbek
 * @created 14/11/2019
 */


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "employee_details", schema = "employee")
@Getter @Setter @NoArgsConstructor
public class EmployeeDetails extends AuditEntity implements Serializable {
	private static final long serialVersionUID = -2461411805344035506L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lt_login")
    private String ltLogin;

    @Column(name = "lt_id")
    @Size(max = 8)
    private String ltId;

    @Column(name = "crm_login")
    private String crmLogin;

    @Column(name = "crm_expiration_date")
    private LocalDate crmAccountExpirationDate;

    @JoinColumn(name = "user_section")
    private String userSection;

    @JoinColumn(name = "user_line")
    private String userLine;

    private String fte;

    @Column(name = "fte_start")
    private String fteStart;

    @Size(max = 4)
    private String goal1;

    @Size(max = 4)
    private String goal2;

    @Size(max = 4)
    private String goal3;

    @Size(max = 4)
    private String goal4;

    @Size(max = 4)
    private String goal5;
    
    public void fire() {
    	this.crmAccountExpirationDate = null;
    	this.fte = null;
    	this.fteStart = null;
    	this.goal1 = null;
    	this.goal2 = null;
    	this.goal3 = null;
    	this.goal4 = null;
    	this.goal5 = null;    	
    }
}
