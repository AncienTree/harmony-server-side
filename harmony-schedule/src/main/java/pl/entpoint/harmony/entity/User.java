package pl.entpoint.harmony.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import pl.entpoint.harmony.entity.enums.Roles;

/**
 * @author Mateusz Dąbek
 * Created on Nov 11, 2019
 * m.dabek@entpoint.pl
 */

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private int id;

	@Column(nullable = false, unique = true)
	private String login;

	@JsonIgnore
	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private boolean status;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Roles role;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	@JsonIgnore
	private Employee employeeId;
	
	public User() {
		
	}	

	public User(String login, String password) {
		this.login = login;
		this.password = password;
		this.status = true;
		this.role = Roles.ROLE_USER;		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Roles getRole() {
		return role;
	}


	public void setRole(Roles role) {
		this.role = role;
	}

	public Employee getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}
			
	public void newUser(User user) {
		user.setEmployeeId( new Employee() );
		user.setRole(Roles.ROLE_USER);		
	}


}
