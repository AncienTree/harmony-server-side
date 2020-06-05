package pl.entpoint.harmony.service.employee.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.employee.EmployeeInfo;
import pl.entpoint.harmony.util.exception.employee.EmployeeNotFoundException;

import java.util.Map;
import java.util.Optional;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@Service
public class EmployeeInfoServiceImpl implements EmployeeInfoService{

    EmployeeInfoRepository employeeInfoRepository;

    @Autowired
    public EmployeeInfoServiceImpl(EmployeeInfoRepository employeeInfoRepository) {
        this.employeeInfoRepository = employeeInfoRepository;
    }

    @Override
    public EmployeeInfo getEmployeeInfo(Long id) {
        return employeeInfoRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public void change(Map<String, String> employeeInfo) {
        Long id = Long.parseLong(employeeInfo.get("id"));
        Optional<EmployeeInfo> info = Optional.ofNullable(employeeInfoRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id)));
        EmployeeInfo emplInfo = null;
        if (info.isPresent()){
            emplInfo = info.get();
        }

        // Zmiany
        assert emplInfo != null;

        boolean agreement = Boolean.parseBoolean(employeeInfo.get("agreement"));
        boolean ppk = Boolean.parseBoolean(employeeInfo.get("ppk"));
        boolean headphones = Boolean.parseBoolean(employeeInfo.get("headphones"));

        emplInfo.setAgreement(agreement);
        emplInfo.setPpk(ppk);
        emplInfo.setHeadphones(headphones);
        emplInfo.setLocker(employeeInfo.get("locker"));
        emplInfo.setIdCard(employeeInfo.get("idCard"));
        emplInfo.setParkingCard(employeeInfo.get("parkingCard"));
        emplInfo.setInfo1(employeeInfo.get("info1"));
        emplInfo.setInfo2(employeeInfo.get("info2"));
        emplInfo.setInfo3(employeeInfo.get("info3"));
        emplInfo.setInfo4(employeeInfo.get("info4"));

        employeeInfoRepository.save(emplInfo);
    }
}
