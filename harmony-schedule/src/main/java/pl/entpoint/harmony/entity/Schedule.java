package pl.entpoint.harmony.entity;

import java.sql.Time;
import java.util.Date;

import javax.persistence.CascadeType;
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
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee empl;
	
	@Column(nullable = false)
	private Date date;
	
	private Time in;
	
	private Time out;
	
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getIn() {
		return in;
	}

	public void setIn(Time in) {
		this.in = in;
	}

	public Time getOut() {
		return out;
	}

	public void setOut(Time out) {
		this.out = out;
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
	
	/**
	 * @param format "hh:mm:ss"
	*/	
	public Time setTimeAsString(String time) {
		
		return Time.valueOf(time);
	}
	
}
