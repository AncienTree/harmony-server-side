package pl.entpoint.harmony.service.schedule.record;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.dto.Presence;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;
import pl.entpoint.harmony.entity.schedule.enums.ScheduleType;

/**
 * @author Mateusz Dąbek
 * @created 19/11/2019
 */

@Service
public class ScheduleRecordServiceImpl implements ScheduleRecordService {

    final ScheduleRecordRepository scheduleRecordRepository;

    @Autowired
    public ScheduleRecordServiceImpl(ScheduleRecordRepository scheduleRecordRepository) {
        this.scheduleRecordRepository = scheduleRecordRepository;
    }

    @Override
    public List<ScheduleRecord> getScheduleRecordByDateAndEmployee(LocalDate date, Employee employee) {
        return scheduleRecordRepository.findByWorkDateAndEmployee(date, employee);
    }

    @Override
    public List<Presence> getScheduleRecordForPresence(LocalDate date) {
        List<Presence> presences = new ArrayList<>();
        presences.add(new Presence(ScheduleType.DOSTEPNOSC, scheduleRecordRepository.findByWorkDateAndTypes(date, ScheduleType.DOSTEPNOSC)));
        presences.add(new Presence(ScheduleType.DYSPOZYCYJNOSC, scheduleRecordRepository.findByWorkDateAndTypes(date, ScheduleType.DYSPOZYCYJNOSC)));
        presences.add(new Presence(ScheduleType.GRAFIK, scheduleRecordRepository.findByWorkDateAndTypes(date, ScheduleType.GRAFIK)));
        presences.add(new Presence(ScheduleType.OBECNOSC, scheduleRecordRepository.findByWorkDateAndTypes(date, ScheduleType.OBECNOSC)));

        return presences;
    }

    @Override
    public void create(ScheduleRecord record) {
    	scheduleRecordRepository.save(record);
    	
    }
}
