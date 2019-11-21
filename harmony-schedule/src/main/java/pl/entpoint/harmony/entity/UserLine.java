package pl.entpoint.harmony.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Mateusz Dąbek
 * Created on Nov 18, 2019
 * m.dabek@entpoint.pl
 */

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
@Entity
@Table(name = "user_line")
public class UserLine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true, length = 25)
	private String name;
	
	@OneToMany(mappedBy = "UserLine", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<EmployeeDetails> empl;
	
	public UserLine() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EmployeeDetails> getEmpl() {
		return empl;
	}

	public void setEmpl(List<EmployeeDetails> empl) {
		this.empl = empl;
	}
		
}
