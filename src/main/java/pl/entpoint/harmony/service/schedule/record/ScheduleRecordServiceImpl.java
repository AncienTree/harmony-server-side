package pl.entpoint.harmony.service.schedule.record;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;
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
    public List<ScheduleRecord> getScheduleRecodByDateAndEmployee(LocalDate date, Employee employee) {
        return scheduleRecordRepository.findByWorkDateAndEmployee(date, employee);
    }
    
    @Override
    public void create(ScheduleRecord record) {
    	scheduleRecordRepository.save(record);
    	
    }
}
