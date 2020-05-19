package pl.entpoint.harmony.service.employee.leave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.employee.EmployeeLeave;
import pl.entpoint.harmony.util.exception.employee.EmployeeNotFoundException;

import java.util.Optional;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@Service
public class EmployeeLeaveServiceImpl implements EmployeeLeaveService {

    EmployeeLeaveRepository employeeLeaveRepository;

    @Autowired
    public EmployeeLeaveServiceImpl(EmployeeLeaveRepository employeeLeaveRepository) {
        this.employeeLeaveRepository = employeeLeaveRepository;
    }

    @Override
    public EmployeeLeave getEmployeeLeave(Long id) {
        return employeeLeaveRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public void change(EmployeeLeave employeeLeave) {
        Optional<EmployeeLeave> leave = Optional.ofNullable(employeeLeaveRepository.findById(employeeLeave.getId())
                .orElseThrow(() -> new EmployeeNotFoundException(employeeLeave.getId())));
        EmployeeLeave emplLeave = null;
        if (leave.isPresent()){
            emplLeave = leave.get();
        }
        // Zmiany
        assert emplLeave != null;

        emplLeave.setNormal(employeeLeave.getNormal());
        emplLeave.setUz(employeeLeave.getUz());
        emplLeave.setAdditional(employeeLeave.getAdditional());
        emplLeave.setPastYears(employeeLeave.getPastYears());

        employeeLeaveRepository.save(emplLeave);
    }
}
