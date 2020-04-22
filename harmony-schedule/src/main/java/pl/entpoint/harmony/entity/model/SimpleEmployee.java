package pl.entpoint.harmony.entity.model;

import lombok.Getter;
import lombok.Setter;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.employee.enums.WorkStatus;

import java.io.Serializable;

@Getter
@Setter
public class SimpleEmployee implements Serializable {
	private static final long serialVersionUID = 3650577428665471954L;
	private Long id;
    private String fullName;
    private String position;
    private WorkStatus workStatus;

    public SimpleEmployee(Employee employee) {
        id = employee.getId();
        fullName = employee.getLastName() + " " + employee.getFirstName();
        position = employee.getPosition();
        workStatus = employee.getWorkStatus();
    }
}
