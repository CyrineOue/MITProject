package tn.MITProject.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table
public class CompanyClient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idClientC")
	private long idClientC;
	private String passwordC;
	private String activityArea;
	private String brand;
	private Date creationDate;
	private long fax;
	private String email;
	private int employeesNb ;
	private int nbDeclaredSinistersC;
	public String getPasswordC() {
		return passwordC;
	}
	public void setPasswordC(String passwordC) {
		this.passwordC = passwordC;
	}
	public String getActivityArea() {
		return activityArea;
	}
	public void setActivityArea(String activityArea) {
		this.activityArea = activityArea;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public long getFax() {
		return fax;
	}
	public void setFax(long fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEmployeesNb() {
		return employeesNb;
	}
	public void setEmployeesNb(int employeesNb) {
		this.employeesNb = employeesNb;
	}
	public int getNbDeclaredSinistersC() {
		return nbDeclaredSinistersC;
	}
	public void setNbDeclaredSinistersC(int nbDeclaredSinistersC) {
		this.nbDeclaredSinistersC = nbDeclaredSinistersC;
	}
	


}
