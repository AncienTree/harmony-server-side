package pl.entpoint.harmony.entity.pojo.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;
import java.time.LocalDate;

/**
 * @author Mateusz Dąbek
 * @created 18.10.2020
 */

@Data @NoArgsConstructor
public class EmployeePojo {
    private Long id;
    private String firstName;
    private String lastName;
    private String pesel;
    private String sex;
    private LocalDate birthday;
    private String email;
    private Float etat;
    private String position;
    private String contractPosition;
    private WorkStatus workStatus;
    private String contractType;
    private String basicUnit;
    private String unit;
    private LocalDate startWorkDate;
    private LocalDate endWorkDate;
    private LocalDate startContractDate;
    private LocalDate endContractDate;
}
