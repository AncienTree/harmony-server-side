package pl.entpoint.harmony.entity.pojo.dbview;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 23/05/2020
 */

@Entity
@Table(name = "hr_table_v" ,schema = "employee")
@Immutable
@Getter @NoArgsConstructor
public class HrTable {
    @Id
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    private String position;
    
    @Column(name = "lt_login")
    private String ltLogin;
    
    private String etat;

    @Column(name = "user_section")
    private String userSection;

    @Column(name = "user_line")
    private String userLine;

    @Column(name = "work_status")
    private String workStatus;

    @Column(name = "contract_type")
    private String contractType;

    @Column(name = "start_work_date")
    private LocalDate startWorkDate;

    @Column(name = "end_work_date")
    private LocalDate endWorkDate;

    @Column(name = "start_contract_date")
    private LocalDate startContractDate;

    @Column(name = "end_contract_date")
    private LocalDate endContractDate;

    @Column(name = "crm_expiration_date")
    private LocalDate crmExpirationDate;

    private String email;

    private int leave;
}
