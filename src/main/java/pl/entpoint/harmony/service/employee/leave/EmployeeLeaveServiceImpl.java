package pl.entpoint.harmony.service.employee.leave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.employee.EmployeeLeave;
import pl.entpoint.harmony.util.exception.employee.EmployeeNotFoundException;

import java.util.Map;
import java.util.Optional;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@Service
public class EmployeeLeaveServiceImpl implements EmployeeLeaveService {

    final EmployeeLeaveRepository employeeLeaveRepository;

    @Autowired
    public EmployeeLeaveServiceImpl(EmployeeLeaveRepository employeeLeaveRepository) {
        this.employeeLeaveRepository = employeeLeaveRepository;
    }

    @Override
    public EmployeeLeave getEmployeeLeave(Long id) {
        return employeeLeaveRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public void change(Map<String, String> employeeLeave) {
        Long id = Long.parseLong(employeeLeave.get("id"));
        Optional<EmployeeLeave> leave = Optional.ofNullable(employeeLeaveRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id)));
        EmployeeLeave emplLeave = null;
        if (leave.isPresent()){
            emplLeave = leave.get();
        }

        // Zmiany
        assert emplLeave != null;

        emplLeave.setNormal(Integer.parseInt(employeeLeave.get("normal")));
        emplLeave.setUz(Integer.parseInt(employeeLeave.get("uz")));
        emplLeave.setAdditional(Integer.parseInt(employeeLeave.get("add")));
        emplLeave.setPastYears(Integer.parseInt(employeeLeave.get("past")));

        employeeLeaveRepository.save(emplLeave);
    }
}
