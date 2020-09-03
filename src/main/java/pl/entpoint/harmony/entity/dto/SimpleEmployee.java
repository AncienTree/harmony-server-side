package pl.entpoint.harmony.entity.dto;

import lombok.Getter;
import lombok.Setter;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;

import java.io.Serializable;
import java.time.LocalDate;

@Getter @Setter
public class SimpleEmployee implements Serializable {
	private static final long serialVersionUID = 3650577428665471954L;
	private Long id;
    private String fullName;
    private String position;
    private WorkStatus workStatus;
    private float etat;
    private LocalDate startWorkDate;
    private LocalDate endWorkDate;
    private String contractType;
    private String userSection;
    private String userLine;
    private String fte;
    private String fteStart;

    public SimpleEmployee(Employee employee) {
        id = employee.getId();
        fullName = employee.getLastName() + " " + employee.getFirstName();
        position = employee.getPosition();
        workStatus = employee.getWorkStatus();

        contractType = employee.getContractType();
        startWorkDate = employee.getStartWorkDate();
        endWorkDate = employee.getEndWorkDate();
        userSection = employee.getEmployeeDetails().getUserSection();
        userLine = employee.getEmployeeDetails().getUserLine();
        fte = employee.getEmployeeDetails().getFte();
        fteStart = employee.getEmployeeDetails().getFteStart();
        
        if (employee.getEtat() == null) {
        	etat = -1;
        } else {
        	etat = employee.getEtat();
        }
        
    }
}
