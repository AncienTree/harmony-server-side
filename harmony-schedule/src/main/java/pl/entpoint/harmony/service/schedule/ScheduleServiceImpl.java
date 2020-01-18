package pl.entpoint.harmony.service.schedule;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.models.employee.Employee;
import pl.entpoint.harmony.models.schedule.ScheduleRecord;
import pl.entpoint.harmony.models.schedule.enums.ScheduleStatus;

/**
 * @author Mateusz Dąbek
 * @created 19/11/2019
 */

@Service
public class ScheduleServiceImpl implements ScheduleService {

    ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<ScheduleRecord> getScheduleByStatus(ScheduleStatus status) {
        return scheduleRepository.findByStatus(status);
    }

    @Override
    public List<ScheduleRecord> getScheduleBetweenDate(LocalDate startDate, LocalDate endDate) {
        return scheduleRepository.findByWorkDateBetween(startDate, endDate);
    }

    @Override
    public List<ScheduleRecord> getScheduleByEmployee(Employee employee) {
        return scheduleRepository.findByEmployee(employee);
    }

    @Override
    public List<ScheduleRecord> getScheduleByDate(LocalDate date) {
        return scheduleRepository.findByWorkDate(date);
    }

    @Override
    public List<ScheduleRecord> getScheduleByDateAndEmployee(LocalDate date, Employee employee) {
        return scheduleRepository.findByWorkDateAndEmployee(date, employee);
    }

    @Override
    public List<ScheduleRecord> getScheduleBetweenDateAndStatus(LocalDate startDate, LocalDate endDate,
                                                                ScheduleStatus status) {
        return scheduleRepository.findByWorkDateBetweenAndStatus(startDate, endDate, status);
    }

    @Override
    public List<ScheduleRecord> getScheduleBetweenDateAndEmployee(LocalDate startDate, LocalDate endDate, Employee employee) {
        return scheduleRepository.findByWorkDateBetweenAndEmployee(startDate, endDate, employee);
    }

}
