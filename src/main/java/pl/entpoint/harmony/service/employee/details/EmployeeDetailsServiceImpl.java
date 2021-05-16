package pl.entpoint.harmony.service.employee.details;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.employee.EmployeeDetails;
import pl.entpoint.harmony.entity.pojo.controller.DetailsPojo;
import pl.entpoint.harmony.util.exception.employee.EmployeeNotFoundException;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@Service
@AllArgsConstructor
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

    final EmployeeDetailsRepository employeeDetailsRepository;

    @Override
    public EmployeeDetails getEmployeeDetails(Long id) {
        return employeeDetailsRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public void change(DetailsPojo data) {
        EmployeeDetails employeeDetails = employeeDetailsRepository.findById(data.getId())
                .orElseThrow(() -> new EmployeeNotFoundException(data.getId()));

        employeeDetails.setLtId(data.getLtId());
        employeeDetails.setLtLogin(data.getLtLogin());
        employeeDetails.setCrmLogin(data.getCrmLogin());
        employeeDetails.setCrmAccountExpirationDate(data.getCrmAccountExpirationDate());
        employeeDetails.setUserLine(data.getUserLine());
        employeeDetails.setUserSection(data.getUserSection());
        employeeDetails.setFte(data.getFte());
        employeeDetails.setFteStart(data.getFteStart());
        employeeDetails.setGoal1(data.getGoal1());
        employeeDetails.setGoal2(data.getGoal2());
        employeeDetails.setGoal3(data.getGoal3());
        employeeDetails.setGoal4(data.getGoal4());
        employeeDetails.setGoal5(data.getGoal5());

        employeeDetailsRepository.save(employeeDetails);
    }
}
