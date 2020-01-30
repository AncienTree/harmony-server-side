package pl.entpoint.harmony.service.schedule;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;
import pl.entpoint.harmony.entity.schedule.enums.ScheduleStatus;

/**
 * @author Mateusz Dąbek
 * @created 19/11/2019
 */

@Service
public class ScheduleRecordServiceImpl implements ScheduleRecordService {

    ScheduleRecordRepository scheduleRecordRepository;

    @Autowired
    public ScheduleRecordServiceImpl(ScheduleRecordRepository scheduleRecordRepository) {
        this.scheduleRecordRepository = scheduleRecordRepository;
    }

    @Override
    public List<ScheduleRecord> getScheduleByStatus(ScheduleStatus status) {
        return scheduleRecordRepository.findByStatus(status);
    }

    @Override
    public List<ScheduleRecord> getScheduleBetweenDate(LocalDate startDate, LocalDate endDate) {
        return scheduleRecordRepository.findByWorkDateBetween(startDate, endDate);
    }

    @Override
    public List<ScheduleRecord> getScheduleByEmployee(Employee employee) {
        return scheduleRecordRepository.findByEmployee(employee);
    }

    @Override
    public List<ScheduleRecord> getScheduleByDate(LocalDate date) {
        return scheduleRecordRepository.findByWorkDate(date);
    }

    @Override
    public List<ScheduleRecord> getScheduleByDateAndEmployee(LocalDate date, Employee employee) {
        return scheduleRecordRepository.findByWorkDateAndEmployee(date, employee);
    }

    @Override
    public List<ScheduleRecord> getScheduleBetweenDateAndStatus(LocalDate startDate, LocalDate endDate,
                                                                ScheduleStatus status) {
        return scheduleRecordRepository.findByWorkDateBetweenAndStatus(startDate, endDate, status);
    }

    @Override
    public List<ScheduleRecord> getScheduleBetweenDateAndEmployee(LocalDate startDate, LocalDate endDate, Employee employee) {
        return scheduleRecordRepository.findByWorkDateBetweenAndEmployee(startDate, endDate, employee);
    }

}
