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

}
