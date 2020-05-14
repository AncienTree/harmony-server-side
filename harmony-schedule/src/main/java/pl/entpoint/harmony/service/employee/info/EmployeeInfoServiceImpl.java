package pl.entpoint.harmony.service.employee.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.employee.EmployeeInfo;
import pl.entpoint.harmony.util.exception.employee.EmployeeNotFoundException;

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
    public void change(EmployeeInfo employeeInfo) {
        Optional<EmployeeInfo> info = Optional.ofNullable(employeeInfoRepository.findById(employeeInfo.getId())
                .orElseThrow(() -> new EmployeeNotFoundException(employeeInfo.getId())));
        EmployeeInfo emplInfo = null;
        if (info.isPresent()){
            emplInfo = info.get();
        }
        // Zmiany
        assert emplInfo != null;

        emplInfo.setAgreement(employeeInfo.isAgreement());
        emplInfo.setPpk(employeeInfo.isPpk());
        emplInfo.setHeadphones(employeeInfo.isHeadphones());
        emplInfo.setLocker(employeeInfo.getLocker());
        emplInfo.setIdCard(employeeInfo.getIdCard());
        emplInfo.setParkingCard(employeeInfo.getParkingCard());
        emplInfo.setInfo1(employeeInfo.getInfo1());
        emplInfo.setInfo2(employeeInfo.getInfo2());
        emplInfo.setInfo3(employeeInfo.getInfo3());
        emplInfo.setInfo4(employeeInfo.getInfo4());

        employeeInfoRepository.save(emplInfo);
    }
}
