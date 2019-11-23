package pl.entpoint.harmony.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.entpoint.harmony.entity.Employee;
import pl.entpoint.harmony.entity.Schedule;
import pl.entpoint.harmony.entity.enums.ScheduleStatus;

/**
 * @author Mateusz Dąbek
 * Created on Nov 19, 2019
 * m.dabek@entpoint.pl
 */

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
	
	public List<Schedule> findByStatus(ScheduleStatus status);
	
	public List<Schedule> findByWorkDateBetween(LocalDate startDate, LocalDate endDate);
	
	public List<Schedule> findByEmpl(Employee empl);

	public List<Schedule> findByWorkDate(LocalDate date);

	public List<Schedule> findByWorkDateAndEmpl(LocalDate date, Employee empl);
	
	public List<Schedule> findByWorkDateBetweenAndStatus(LocalDate startDate, LocalDate endDate, ScheduleStatus status);

	public List<Schedule> findByWorkDateBetweenAndEmpl(LocalDate startDate, LocalDate endDate, Employee empl);

}
