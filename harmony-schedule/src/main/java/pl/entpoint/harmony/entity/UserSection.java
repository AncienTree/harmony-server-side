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

/**
 * @author Mateusz Dąbek
 * Created on Nov 18, 2019
 * m.dabek@entpoint.pl
 */

@Entity
@Table(name = "user_section")
public class UserSection {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true, length = 25)
	private String name;
	
	@OneToMany(mappedBy = "userSection", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<EmployeeDetails> empl;
	
	public UserSection() {
		
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
