package pl.entpoint.harmony.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mateusz Dąbek
 * Created on Nov 14, 2019
 * m.dabek@entpoint.pl
 */

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
	
	@Column(name = "user_section")
	private String userSection;
	
	@Column(name = "user_line")
	private String UserLine;
	
	private float fte;
	
	@Column(name = "fte_start")
	private float fteStart;
	
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

	public String getUserSection() {
		return userSection;
	}

	public void setUserSection(String userSection) {
		this.userSection = userSection;
	}

	public String getUserLine() {
		return UserLine;
	}

	public void setUserLine(String userLine) {
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

	@Override
	public String toString() {
		return "EmployeeDetails [id=" + id + ", ltLogin=" + ltLogin + ", ltId=" + ltId + ", crmLogin=" + crmLogin
				+ ", userSection=" + userSection + ", UserLine=" + UserLine + ", fte=" + fte + ", fteStart=" + fteStart
				+ ", update=" + update + ", goal1=" + goal1 + ", goal2=" + goal2 + ", goal3=" + goal3 + ", goal4="
				+ goal4 + ", goal5=" + goal5 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((UserLine == null) ? 0 : UserLine.hashCode());
		result = prime * result + ((crmLogin == null) ? 0 : crmLogin.hashCode());
		result = prime * result + Float.floatToIntBits(fte);
		result = prime * result + Float.floatToIntBits(fteStart);
		result = prime * result + goal1;
		result = prime * result + goal2;
		result = prime * result + goal3;
		result = prime * result + goal4;
		result = prime * result + goal5;
		result = prime * result + id;
		result = prime * result + ((ltId == null) ? 0 : ltId.hashCode());
		result = prime * result + ((ltLogin == null) ? 0 : ltLogin.hashCode());
		result = prime * result + ((update == null) ? 0 : update.hashCode());
		result = prime * result + ((userSection == null) ? 0 : userSection.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDetails other = (EmployeeDetails) obj;		
		if (crmLogin == null) {
			if (other.crmLogin != null)
				return false;
		} else if (!crmLogin.equals(other.crmLogin))
			return false;
		if (id != other.id)
			return false;
		if (ltId == null) {
			if (other.ltId != null)
				return false;
		} else if (!ltId.equals(other.ltId))
			return false;
		if (ltLogin == null) {
			if (other.ltLogin != null)
				return false;
		} else if (!ltLogin.equals(other.ltLogin))
			return false;		
		return true;
	}
}
