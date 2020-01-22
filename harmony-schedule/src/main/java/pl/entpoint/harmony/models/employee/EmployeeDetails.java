package pl.entpoint.harmony.models.employee;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Long id;

    @Column(name = "lt_login")
    private String ltLogin;

    @Column(name = "lt_id", length = 8)
    private String ltId;

    @Column(name = "crm_login")
    private String crmLogin;

    @JoinColumn(name = "user_section")
    private String userSection;

    @JoinColumn(name = "user_line")
    private String userLine;

    private String fte;

    @Column(name = "fte_start")
    private String fteStart;

    @Column(name = "update_date")
    private LocalDate updateDate;

    @Column(length = 4)
    private String goal1;

    @Column(length = 4)
    private String goal2;

    @Column(length = 4)
    private String goal3;

    @Column(length = 4)
    private String goal4;

    @Column(length = 4)
    private String goal5;
}
