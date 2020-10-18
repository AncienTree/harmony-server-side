package pl.entpoint.harmony.service.employee.leave;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.employee.EmployeeLeave;
import pl.entpoint.harmony.entity.pojo.controller.LeavePojo;
import pl.entpoint.harmony.util.exception.employee.EmployeeNotFoundException;

import java.util.Optional;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@Service
@AllArgsConstructor
public class EmployeeLeaveServiceImpl implements EmployeeLeaveService {

    private final EmployeeLeaveRepository employeeLeaveRepository;

    @Override
    public EmployeeLeave getEmployeeLeave(Long id) {
        return employeeLeaveRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public void change(LeavePojo leave) {
        EmployeeLeave employeeLeave = employeeLeaveRepository.findById(leave.getId())
                .orElseThrow(() -> new EmployeeNotFoundException(leave.getId()));

        employeeLeave.setNormal(leave.getNormal());
        employeeLeave.setUz(leave.getUz());
        employeeLeave.setAdditional(leave.getAdditional());
        employeeLeave.setPastYears(leave.getPastYears());

        employeeLeaveRepository.save(employeeLeave);
    }
}
