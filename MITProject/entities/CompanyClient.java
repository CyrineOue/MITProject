package tn.MITProject.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity 
@Table(name="CompanyClient")
public class CompanyClient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idClientC")
	private long idClientC;
	private String activityArea;
	private String brand;
	private Date creationDate;
	private long fax;
	private int employeesNb ;
	private int nbDeclaredSinistersC;
	
	@OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
	private Log logClientC;
	
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
	public long getIdClientC() {
		return idClientC;
	}
	public void setIdClientC(long idClientC) {
		this.idClientC = idClientC;
	}
	public Log getLogClientC() {
		return logClientC;
	}
	public void setLogClientC(Log logClientC) {
		this.logClientC = logClientC;
	}
	public CompanyClient() {
		super();
		// TODO Auto-generated constructor stub
	}
	


}
