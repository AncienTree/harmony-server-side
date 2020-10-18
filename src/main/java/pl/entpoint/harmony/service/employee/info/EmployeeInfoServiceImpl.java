package pl.entpoint.harmony.service.employee.info;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.entpoint.harmony.entity.employee.EmployeeInfo;
import pl.entpoint.harmony.entity.pojo.controller.InfoPojo;
import pl.entpoint.harmony.util.exception.employee.EmployeeNotFoundException;

import java.util.Optional;

/**
 * @author Mateusz Dąbek
 * @created 14/05/2020
 */

@Service
@AllArgsConstructor
public class EmployeeInfoServiceImpl implements EmployeeInfoService{

    final EmployeeInfoRepository employeeInfoRepository;

    @Override
    public EmployeeInfo getEmployeeInfo(Long id) {
        return employeeInfoRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public void change(InfoPojo info) {
        EmployeeInfo employeeInfo = employeeInfoRepository.findById(info.getId())
                        .orElseThrow(() -> new EmployeeNotFoundException(info.getId()));

        employeeInfo.setAgreement(info.isAgreement());
        employeeInfo.setPpk(info.isPpk());
        employeeInfo.setHeadphones(info.isHeadphones());
        employeeInfo.setLocker(info.getLocker());
        employeeInfo.setIdCard(info.getIdCard());
        employeeInfo.setParkingCard(info.getParkingCard());
        employeeInfo.setInfo1(info.getInfo1());
        employeeInfo.setInfo2(info.getInfo2());
        employeeInfo.setInfo3(info.getInfo3());
        employeeInfo.setInfo4(info.getInfo4());

        employeeInfoRepository.save(employeeInfo);
    }
}
