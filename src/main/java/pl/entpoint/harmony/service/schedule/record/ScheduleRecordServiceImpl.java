package pl.entpoint.harmony.service.schedule.record;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.dto.Presence;
import pl.entpoint.harmony.entity.dto.Record;
import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;
import pl.entpoint.harmony.entity.schedule.ScheduleSummary;
import pl.entpoint.harmony.entity.schedule.enums.ScheduleType;
import pl.entpoint.harmony.service.employee.EmployeeService;
import pl.entpoint.harmony.service.schedule.summary.ScheduleSummaryService;
import pl.entpoint.harmony.util.ConvertData;

/**
 * @author Mateusz Dąbek
 * @created 19/11/2019
 */

@Service
public class ScheduleRecordServiceImpl implements ScheduleRecordService {

    final ScheduleRecordRepository scheduleRecordRepository;
    final ScheduleSummaryService scheduleSummaryService;
    final EmployeeService employeeService; 

    @Autowired
    public ScheduleRecordServiceImpl(ScheduleRecordRepository scheduleRecordRepository, ScheduleSummaryService scheduleSummaryService, EmployeeService employeeService) {
        this.scheduleSummaryService = scheduleSummaryService;
		this.employeeService = employeeService;
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
    public void create(Record record) {
        ScheduleRecord scheduleRecord = new ScheduleRecord();
        Employee employee = employeeService.getEmployeeNotDecrypted(record.getEmployee());
        
        scheduleRecord.setEmployee(employee);
        scheduleRecord.setStatus(record.getStatus());
        scheduleRecord.setTypes(record.getTypes());
        scheduleRecord.setStartWork(LocalTime.parse(record.getStartWork()));
        scheduleRecord.setEndWork(LocalTime.parse(record.getEndWork()));
        scheduleRecord.setWorkDate(LocalDate.parse(record.getWorkDate()));

        // Dodanie rekordu do tabeli mapującej
        ScheduleSummary scheduleSummary = scheduleSummaryService.getScheduleByDateAndEmployee(ConvertData.getFirstDayOfMonth(record.getWorkDate()), employee);
        scheduleSummary.getScheduleRecords().add(scheduleRecord);
        
    	scheduleRecordRepository.save(scheduleRecord);
    }

    @Override
    public void update(Record record) {
    	System.out.println("Aktualizacja rekordu");
    	System.out.println(record);
        Optional<ScheduleRecord> scheduleRecord;
        scheduleRecord = Optional.ofNullable(scheduleRecordRepository.findById(record.getId()).orElseThrow(
                () -> new IllegalArgumentException("Nieznaleziono rekordu o podanym ID: " + record.getId())));
        ScheduleRecord updatedRecord = scheduleRecord.get();

        updatedRecord.setStatus(record.getStatus());
        updatedRecord.setStartWork(LocalTime.parse(record.getStartWork()));
        updatedRecord.setEndWork(LocalTime.parse(record.getEndWork()));

        scheduleRecordRepository.save(updatedRecord);
    }
}
