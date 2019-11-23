package pl.entpoint.harmony.service.impl;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.entpoint.harmony.entity.Employee;
import pl.entpoint.harmony.entity.Schedule;
import pl.entpoint.harmony.entity.enums.ScheduleStatus;
import pl.entpoint.harmony.repository.ScheduleRepository;
import pl.entpoint.harmony.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Override
	public List<Schedule> getScheduleByStatus(ScheduleStatus status) {
		return scheduleRepository.findByStatus(status);
	}

	@Override
	public List<Schedule> getScheduleBetweenDate(LocalDate startDate, LocalDate endDate) {
		return scheduleRepository.findByWorkDateBetween(startDate, endDate);
	}

	@Override
	public List<Schedule> getScheduleByEmployee(Employee empl) {
		return scheduleRepository.findByEmpl(empl);
	}

	@Override
	public List<Schedule> getScheduleByDate(LocalDate date) {
		return scheduleRepository.findByWorkDate(date);
	}

	@Override
	public List<Schedule> getScheduleByDateAndEmployee(LocalDate date, Employee empl) {
		return scheduleRepository.findByWorkDateAndEmpl(date, empl);
	}

	@Override
	public List<Schedule> getScheduleBetweenDateAndStatus(LocalDate startDate, LocalDate endDate,
			ScheduleStatus status) {
		return scheduleRepository.findByWorkDateBetweenAndStatus(startDate, endDate, status);
	}

	@Override
	public List<Schedule> getScheduleBetweenDateAndEmployee(LocalDate startDate, LocalDate endDate, Employee empl) {
		return scheduleRepository.findByWorkDateBetweenAndEmpl(startDate, endDate, empl);
	}
	
}
