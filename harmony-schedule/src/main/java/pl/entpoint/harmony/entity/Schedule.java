package pl.entpoint.harmony.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pl.entpoint.harmony.entity.enums.ScheduleStatus;
import pl.entpoint.harmony.entity.enums.ScheduleType;

/**
 * @author Mateusz Dąbek
 * Created on Nov 16, 2019
 * m.dabek@entpoint.pl
 */

@Entity
@Table(name = "schedule")
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee empl;
	
	@Column(name = "work_date",nullable = false)
	private LocalDate workDate;
	
	@Column(name = "start_work",nullable = false)
	private LocalTime startWork;
	
	@Column(name = "end_work",nullable = false)
	private LocalTime endWork;
	
	@Enumerated(EnumType.STRING)
	private ScheduleStatus status;
	
	@Enumerated(EnumType.STRING)
	private ScheduleType type;
	
	public Schedule() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Employee getEmpl() {
		return empl;
	}

	public void setEmpl(Employee empl) {
		this.empl = empl;
	}

	public LocalDate getWorkDate() {
		return workDate;
	}

	public void setWorkDate(LocalDate workDate) {
		this.workDate = workDate;
	}
	
	public LocalTime getStartWork() {
		return startWork;
	}

	public void setStartWork(LocalTime startWork) {
		this.startWork = startWork;
	}

	public LocalTime getEndWork() {
		return endWork;
	}

	public void setEndWork(LocalTime endWork) {
		this.endWork = endWork;
	}

	public ScheduleStatus getStatus() {
		return status;
	}

	public void setStatus(ScheduleStatus status) {
		this.status = status;
	}

	public ScheduleType getType() {
		return type;
	}

	public void setType(ScheduleType type) {
		this.type = type;
	}	

}
