package pl.entpoint.harmony.models.employee;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.entpoint.harmony.models.settings.branch.UserSection;

/**
 * @author Mateusz Dąbek
 * @created 14/11/2019
 */


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "employee_details")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "lt_login")
    private String ltLogin;

    @Column(name = "lt_id", length = 8)
    private int ltId;

    @Column(name = "crm_login")
    private String crmLogin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_section")
    private UserSection userSection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_line")
    private pl.entpoint.harmony.models.settings.branch.UserLine UserLine;

    private float fte;

    @Column(name = "fte_start")
    private float fteStart;

    //TODO automatycznie uzupelnij date przy aktualizacji danych
    @Column(name = "update_date")
    private LocalDate update;

    private int goal1;

    private int goal2;

    private int goal3;

    private int goal4;

    private int goal5;

}
