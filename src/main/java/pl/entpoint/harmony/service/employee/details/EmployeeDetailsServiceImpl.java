package pl.entpoint.harmony.service.employee.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.employee.EmployeeDetails;
import pl.entpoint.harmony.util.exception.employee.EmployeeNotFoundException;

import java.time.LocalDate;
import java.util.Map;
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
    public void change(Map<String, String> employeeDetails) {
        Long id = Long.parseLong(employeeDetails.get("id"));
        Optional<EmployeeDetails> details = Optional.ofNullable(employeeDetailsRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id)));
        EmployeeDetails emplDeta = null;
        if (details.isPresent()){
            emplDeta = details.get();
        }

        // Zmiany
        assert emplDeta != null;

        LocalDate crmExp = LocalDate.parse(employeeDetails.get("crmExp"));

        emplDeta.setLtId(employeeDetails.get("ltId"));
        emplDeta.setLtLogin(employeeDetails.get("ltLogin"));
        emplDeta.setCrmLogin(employeeDetails.get("crmLogin"));
        emplDeta.setCrmAccountExpirationDate(crmExp);
        emplDeta.setUserLine(employeeDetails.get("userLine"));
        emplDeta.setUserSection(employeeDetails.get("userSection"));
        emplDeta.setFte(employeeDetails.get("fte"));
        emplDeta.setFteStart(employeeDetails.get("fteStart"));
        emplDeta.setGoal1(employeeDetails.get("goal1"));
        emplDeta.setGoal2(employeeDetails.get("goal2"));
        emplDeta.setGoal3(employeeDetails.get("goal3"));
        emplDeta.setGoal4(employeeDetails.get("goal4"));
        emplDeta.setGoal5(employeeDetails.get("goal5"));

        employeeDetailsRepository.save(emplDeta);
    }
}
