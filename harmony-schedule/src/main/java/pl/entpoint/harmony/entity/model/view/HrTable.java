package pl.entpoint.harmony.entity.model.view;

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
    Long id;

    @Column(name = "full_name")
    String fullName;

    String position;

    @Column(name = "user_section")
    String userSection;

    @Column(name = "user_line")
    String userLine;

    @Column(name = "work_status")
    String workStatus;

    String sex;

    LocalDate birthday;

    @Column(name = "contract_type")
    String contractType;

    @Column(name = "start_contract_date")
    LocalDate startContractDate;

    @Column(name = "end_contract_date")
    LocalDate endContractDate;

    @Column(name = "crm_expiration_date")
    LocalDate crmExpirationDate;

    String fte;

    String email;

    String city;

    int leave;
}
