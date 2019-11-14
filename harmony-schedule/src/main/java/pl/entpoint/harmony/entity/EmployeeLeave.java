package pl.entpoint.harmony.entity;

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
@Table(name = "employee_leave")
public class EmployeeLeave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int total;
	
	private int normal;
	
	private int uz;
	
	@Column(name = "last_years")
	private int lastYears;

	public EmployeeLeave() {
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getNormal() {
		return normal;
	}

	public void setNormal(int normal) {
		this.normal = normal;
	}

	public int getUz() {
		return uz;
	}

	public void setUz(int uz) {
		this.uz = uz;
	}

	public int getLastYears() {
		return lastYears;
	}

	public void setLastYears(int lastYears) {
		this.lastYears = lastYears;
	}

	@Override
	public String toString() {
		return "EmployeeLeave [id=" + id + ", total=" + total + ", normal=" + normal + ", uz=" + uz + ", lastYears="
				+ lastYears + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + lastYears;
		result = prime * result + normal;
		result = prime * result + total;
		result = prime * result + uz;
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
		EmployeeLeave other = (EmployeeLeave) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
