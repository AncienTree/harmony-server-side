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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import pl.entpoint.harmony.entity.enums.Roles;

/**
 * @author Mateusz Dąbek
 * Created on Nov 11, 2019
 * m.dabek@entpoint.pl
 */

@Entity
@Table(name = "user")
@Getter @Setter @NoArgsConstructor
@Slf4j
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private int id;

	@Column(nullable = false, unique = true, length = 50)
	private String login;

	// TODO BCrypt 
	@JsonIgnore
	@Column(nullable = false, length = 68)
	private String password;

	@Column(nullable = false)
	private boolean status;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Roles role;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	@JsonIgnore
	private Employee employee;

	public User(String login, String password) {
		this.login = login;
		this.password = password;
		this.status = true;
		this.role = Roles.ROLE_USER;		
	}
			
	public void newUser(User user) {
		user.setEmployee( new Employee() );
		user.setRole(Roles.ROLE_USER);		
		log.info("Stworzono nowego uzytkownika " + user.getLogin());
	}


}
