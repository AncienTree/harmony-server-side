package pl.entpoint.harmony.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Mateusz Dąbek
 * Created on Nov 14, 2019
 * m.dabek@entpoint.pl
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "employee_info")
public class EmployeeInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private boolean agreement;
	
	private boolean ppk;

	private boolean headphones;
	
	private String locker;
	
	@Column(name = "id_card")
	private String idCard;
	
	@Column(name = "parking_card")
	private String parkingCard;
	
	private String info1;
	
	private String info2;
	
	private String info3;
	
	private String info4;
	
	public EmployeeInfo() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAgreement() {
		return agreement;
	}

	public void setAgreement(boolean agreement) {
		this.agreement = agreement;
	}

	public boolean isPpk() {
		return ppk;
	}

	public void setPpk(boolean ppk) {
		this.ppk = ppk;
	}

	public boolean isHeadphones() {
		return headphones;
	}

	public void setHeadphones(boolean headphones) {
		this.headphones = headphones;
	}

	public String getLocker() {
		return locker;
	}

	public void setLocker(String locker) {
		this.locker = locker;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getParkingCard() {
		return parkingCard;
	}

	public void setParkingCard(String parkingCard) {
		this.parkingCard = parkingCard;
	}

	public String getInfo1() {
		return info1;
	}

	public void setInfo1(String info1) {
		this.info1 = info1;
	}

	public String getInfo2() {
		return info2;
	}

	public void setInfo2(String info2) {
		this.info2 = info2;
	}

	public String getInfo3() {
		return info3;
	}

	public void setInfo3(String info3) {
		this.info3 = info3;
	}

	public String getInfo4() {
		return info4;
	}

	public void setInfo4(String info4) {
		this.info4 = info4;
	}

	@Override
	public String toString() {
		return "EmployeeInfo [id=" + id + ", agreement=" + agreement + ", ppk=" + ppk + ", headphones=" + headphones
				+ ", locker=" + locker + ", idCard=" + idCard + ", parkingCard=" + parkingCard + ", info1=" + info1
				+ ", info2=" + info2 + ", info3=" + info3 + ", info4=" + info4 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (agreement ? 1231 : 1237);
		result = prime * result + (headphones ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((idCard == null) ? 0 : idCard.hashCode());
		result = prime * result + ((info1 == null) ? 0 : info1.hashCode());
		result = prime * result + ((info2 == null) ? 0 : info2.hashCode());
		result = prime * result + ((info3 == null) ? 0 : info3.hashCode());
		result = prime * result + ((info4 == null) ? 0 : info4.hashCode());
		result = prime * result + ((locker == null) ? 0 : locker.hashCode());
		result = prime * result + ((parkingCard == null) ? 0 : parkingCard.hashCode());
		result = prime * result + (ppk ? 1231 : 1237);
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
		EmployeeInfo other = (EmployeeInfo) obj;
		if (id != other.id)
			return false;		
		return true;
	}
}
