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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mateusz Dąbek
 * Created on Nov 18, 2019
 * m.dabek@entpoint.pl
 */

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
@Entity
@Table(name = "user_section")
@Getter @Setter @NoArgsConstructor
public class UserSection {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true, length = 25)
	private String name;
	
	@OneToMany(mappedBy = "userSection", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<EmployeeDetails> empl;
	
}
