package pl.entpoint.harmony.service.schedule.record;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.employee.Employee;
import pl.entpoint.harmony.entity.pojo.Presence;
import pl.entpoint.harmony.entity.pojo.Record;
import pl.entpoint.harmony.entity.schedule.AbsenceRecord;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;
import pl.entpoint.harmony.entity.schedule.ScheduleSummary;
import pl.entpoint.harmony.entity.schedule.enums.ScheduleStatus;
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
    public ScheduleRecord getScheduleRecordByDateAndEmployeeAndTypes(LocalDate date, Employee employee, ScheduleType type) {
        return scheduleRecordRepository.findByWorkDateAndEmployeeAndTypes(date, employee, type);
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
        Employee employee = employeeService.getEmployeeNotDecrypted(record.getEmployee());

        Optional<ScheduleRecord> recordOpt = Optional.ofNullable(scheduleRecordRepository
                .findByWorkDateAndEmployeeAndTypes(LocalDate.parse(record.getWorkDate()), employee, record.getTypes()));

        if (recordOpt.isPresent()){
          record.setId(recordOpt.get().getId());
          update(record);
        } else {
            ScheduleRecord scheduleRecord = new ScheduleRecord();

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
    
    

    @Override
	public int getCurrentMonthStatus(Employee employee, String type, LocalDate date) {
        LocalDate start = LocalDate.of(date.getYear(), date.getMonth(), 1);
        LocalDate end = LocalDate.of(date.getYear(), date.getMonth(), date.lengthOfMonth());
        
		return getNumbersOfDaysByStatus(employee, type, start, end);
	}

	@Override
	public int getCurrentYearStatus(Employee employee, String type, LocalDate date) {
        LocalDate start = LocalDate.of(date.getYear(), Month.JANUARY, 1);
        LocalDate end = LocalDate.of(date.getYear(), Month.DECEMBER, 31);
        
        return getNumbersOfDaysByStatus(employee, type, start, end);
	}
	
	@Override
    public int getNumbersOfDaysByStatus(Employee employee, String type, LocalDate start, LocalDate end) {
        switch (type) {
            case "work":
                return getSumOfHours(scheduleRecordRepository.findByWorkDateBetweenAndEmployeeAndTypesAndStatus(
                        start, end, employee, ScheduleType.OBECNOSC, ScheduleStatus.P));
            case "leave":
                return getSumOfDays(scheduleRecordRepository.findByWorkDateBetweenAndEmployeeAndTypesAndStatus(
                        start, end, employee, ScheduleType.OBECNOSC, ScheduleStatus.UZ)) +
                        getSumOfDays(scheduleRecordRepository.findByWorkDateBetweenAndEmployeeAndTypesAndStatus(
                                start, end, employee, ScheduleType.OBECNOSC, ScheduleStatus.UW)) +
                        getSumOfDays(scheduleRecordRepository.findByWorkDateBetweenAndEmployeeAndTypesAndStatus(
                                start, end, employee, ScheduleType.OBECNOSC, ScheduleStatus.UK));
            case "absences":
                return getSumOfDays(scheduleRecordRepository.findByWorkDateBetweenAndEmployeeAndTypesAndStatus(
                        start, end, employee, ScheduleType.OBECNOSC, ScheduleStatus.CH)) +
                        getSumOfDays(scheduleRecordRepository.findByWorkDateBetweenAndEmployeeAndTypesAndStatus(
                                start, end, employee, ScheduleType.OBECNOSC, ScheduleStatus.CN)) +
                        getSumOfDays(scheduleRecordRepository.findByWorkDateBetweenAndEmployeeAndTypesAndStatus(
                                start, end, employee, ScheduleType.OBECNOSC, ScheduleStatus.NN));
            default:
                return -1;
        }
    }
	
    @Override
	public void accepteAbsence(AbsenceRecord recordAccepted) {
    	ScheduleRecord record = null;
    	Optional<ScheduleRecord> opt = Optional.ofNullable(scheduleRecordRepository
    			.findByWorkDateAndEmployeeAndTypes(recordAccepted.getWorkDate(), recordAccepted.getEmployee(), ScheduleType.OBECNOSC));
    	if(opt.isPresent()) {
    		record = opt.get();
    		record.setStatus(ScheduleStatus.UW);
    		record.setStartWork(LocalTime.of(0, 0, 0));
    		record.setEndWork(LocalTime.of(0, 0, 0));
    	} else {
    		record = new ScheduleRecord();
    		record.setEmployee(recordAccepted.getEmployee());
    		record.setWorkDate(recordAccepted.getWorkDate());
    		record.setTypes(ScheduleType.OBECNOSC);
    		record.setStatus(ScheduleStatus.UW);
    		record.setStartWork(LocalTime.of(0, 0, 0));
    		record.setEndWork(LocalTime.of(0, 0, 0));
    	}
    	
    	// Dodanie rekordu do tabeli mapującej
        ScheduleSummary scheduleSummary = scheduleSummaryService.getScheduleByDateAndEmployee(ConvertData.getFirstDayOfMonth(recordAccepted.getWorkDate().toString()),
        		recordAccepted.getEmployee());
        scheduleSummary.getScheduleRecords().add(record);
    	
    	scheduleRecordRepository.save(record);
	}

	private int getSumOfHours(List<ScheduleRecord> scheduleRecordList) {
        int sum = 0;

        for (ScheduleRecord record: scheduleRecordList) {
            sum += (record.getEndWork().minusHours(record.getStartWork().getHour())).getHour();
        }
        return sum;
    }

    private int getSumOfDays(List<ScheduleRecord> scheduleRecordList) {
        return scheduleRecordList.size();
    }
}
