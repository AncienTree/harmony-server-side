package pl.entpoint.harmony.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mateusz Dąbek
 * Created on Nov 14, 2019
 * m.dabek@entpoint.pl
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "employee_leave")
@Getter @Setter @NoArgsConstructor
public class EmployeeLeave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int total;
	
	private int normal;
	
	private int uz;
	
	private int additional;
	
	@Column(name = "last_years")
	private int lastYears;
	
}
