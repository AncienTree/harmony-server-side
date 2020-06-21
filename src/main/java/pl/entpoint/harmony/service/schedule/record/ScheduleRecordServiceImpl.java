package pl.entpoint.harmony.service.schedule.record;

import java.sql.Date;
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
    public List<ScheduleRecord> getScheduleRecodByDateAndEmployee(Date date, Employee employee) {
        return scheduleRecordRepository.findByWorkDateAndEmployee(date, employee);
    }
    
    @Override
    public void create(ScheduleRecord record) {
    	scheduleRecordRepository.save(record);
    	
    }
}
