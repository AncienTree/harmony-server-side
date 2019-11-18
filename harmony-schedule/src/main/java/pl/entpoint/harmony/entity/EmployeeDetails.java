package pl.entpoint.harmony.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Mateusz Dąbek
 * Created on Nov 14, 2019
 * m.dabek@entpoint.pl
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "employee_details")
public class EmployeeDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "lt_login")
	private String ltLogin;
	
	@Column(name = "lt_id")
	private String ltId;
	
	@Column(name = "crm_login")
	private String crmLogin;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_section")
	private UserSection userSection;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_line")
	private UserLine UserLine;
	
	private float fte;
	
	@Column(name = "fte_start")
	private float fteStart;
	
	@Column(name = "update_date")
	private Date update;
	
	private int goal1;
	
	private int goal2;
	
	private int goal3;
	
	private int goal4;
	
	private int goal5;	
	
	public EmployeeDetails() {
		
	}	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLtLogin() {
		return ltLogin;
	}

	public void setLtLogin(String ltLogin) {
		this.ltLogin = ltLogin;
	}

	public String getLtId() {
		return ltId;
	}

	public void setLtId(String ltId) {
		this.ltId = ltId;
	}

	public String getCrmLogin() {
		return crmLogin;
	}

	public void setCrmLogin(String crmLogin) {
		this.crmLogin = crmLogin;
	}
	
	public UserSection getUserSection() {
		return userSection;
	}

	public void setUserSection(UserSection userSection) {
		this.userSection = userSection;
	}
	
	public UserLine getUserLine() {
		return UserLine;
	}

	public void setUserLine(UserLine userLine) {
		UserLine = userLine;
	}

	public float getFte() {
		return fte;
	}

	public void setFte(float fte) {
		this.fte = fte;
	}

	public float getFteStart() {
		return fteStart;
	}

	public void setFteStart(float fteStart) {
		this.fteStart = fteStart;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public int getGoal1() {
		return goal1;
	}

	public void setGoal1(int goal1) {
		this.goal1 = goal1;
	}

	public int getGoal2() {
		return goal2;
	}

	public void setGoal2(int goal2) {
		this.goal2 = goal2;
	}

	public int getGoal3() {
		return goal3;
	}

	public void setGoal3(int goal3) {
		this.goal3 = goal3;
	}

	public int getGoal4() {
		return goal4;
	}

	public void setGoal4(int goal4) {
		this.goal4 = goal4;
	}

	public int getGoal5() {
		return goal5;
	}

	public void setGoal5(int goal5) {
		this.goal5 = goal5;
	}

}
