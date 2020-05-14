package pl.entpoint.harmony.service.employee.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.employee.EmployeeDetails;
import pl.entpoint.harmony.util.exception.employee.EmployeeNotFoundException;

import java.util.Optional;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

    EmployeeDetailsRepository employeeDetailsRepository;

    @Autowired
    public EmployeeDetailsServiceImpl(EmployeeDetailsRepository employeeDetailsRepository) {
        this.employeeDetailsRepository = employeeDetailsRepository;
    }

    @Override
    public EmployeeDetails getEmployeeDetails(Long id) {
        return employeeDetailsRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public void change(EmployeeDetails employeeDetails) {
        Optional<EmployeeDetails> details = Optional.ofNullable(employeeDetailsRepository.findById(employeeDetails.getId())
                .orElseThrow(() -> new EmployeeNotFoundException(employeeDetails.getId())));
        EmployeeDetails emplDeta = null;
        if (details.isPresent()){
            emplDeta = details.get();
        }
        // Zmiany
        assert emplDeta != null;

        emplDeta.setLtId(employeeDetails.getLtId());
        emplDeta.setLtLogin(employeeDetails.getLtLogin());
        emplDeta.setCrmLogin(employeeDetails.getCrmLogin());
        emplDeta.setCrmAccountExpirationDate(employeeDetails.getCrmAccountExpirationDate());
        emplDeta.setUserLine(employeeDetails.getUserLine());
        emplDeta.setUserSection(employeeDetails.getUserSection());
        emplDeta.setFte(employeeDetails.getFte());
        emplDeta.setFteStart(employeeDetails.getFteStart());
        emplDeta.setGoal1(employeeDetails.getGoal1());
        emplDeta.setGoal2(employeeDetails.getGoal2());
        emplDeta.setGoal3(employeeDetails.getGoal3());
        emplDeta.setGoal4(employeeDetails.getGoal4());
        emplDeta.setGoal5(employeeDetails.getGoal5());

        employeeDetailsRepository.save(emplDeta);
    }
}
