package tn.MITProject.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name="Salary")
public class Salary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idSalary")
	private long idSalary;
	private long idUser;
	@Enumerated(EnumType.STRING)
	private Role role;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape=Shape.STRING, pattern="dd-MM-yyyy")
	private Date monthStart;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape=Shape.STRING, pattern="dd-MM-yyyy")
	private Date monthEnd;
	private double salaryAmount;
	public long getIdSalary() {
		return idSalary;
	}
	public void setIdSalary(long idSalary) {
		this.idSalary = idSalary;
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Date getMonthStart() {
		return monthStart;
	}
	public void setMonthStart(Date monthStart) {
		this.monthStart = monthStart;
	}
	public Date getMonthEnd() {
		return monthEnd;
	}
	public void setMonthEnd(Date monthEnd) {
		this.monthEnd = monthEnd;
	}
	public double getSalaryAmount() {
		return salaryAmount;
	}
	public void setSalaryAmount(double salaryAmount) {
		this.salaryAmount = salaryAmount;
	}
	public Salary() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
