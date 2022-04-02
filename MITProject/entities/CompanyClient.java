package tn.MITProject.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity 
@Table(name="CompanyClient")
public class CompanyClient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idClientC")
	private long idClientC;
	private String brand;
	@Enumerated (EnumType.STRING)
	private Area activityArea;
    @JsonFormat(shape=Shape.STRING, pattern="dd-MM-yyyy")
	private Date creationDate;
	private int employeesNb ;
	private Long capital;
	private int nbDeclaredSinistersC;
	private boolean archived=false;
	private final LocalDate sbuscriptionDate = LocalDate.now();
	private CategoryClient categoryC;
	private String MailC;
	@OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
	private Log logClientC;
	
		
	public long getIdClientC() {
		return idClientC;
	}

	public void setIdClientC(long idClientC) {
		this.idClientC = idClientC;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Area getActivityArea() {
		return activityArea;
	}

	public void setActivityArea(Area activityArea) {
		this.activityArea = activityArea;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getEmployeesNb() {
		return employeesNb;
	}

	public void setEmployeesNb(int employeesNb) {
		this.employeesNb = employeesNb;
	}

	public Long getCapital() {
		return capital;
	}

	public void setCapital(Long capital) {
		this.capital = capital;
	}
	
	public int getNbDeclaredSinistersC() {
		return nbDeclaredSinistersC;
	}

	public void setNbDeclaredSinistersC(int nbDeclaredSinistersC) {
		this.nbDeclaredSinistersC = nbDeclaredSinistersC;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public Log getLogClientC() {
		return logClientC;
	}

	public void setLogClientC(Log logClientC) {
		this.logClientC = logClientC;
	}

	public LocalDate getSbuscriptionDate() {
		return sbuscriptionDate;
	}

	public CompanyClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompanyClient(long idClientC, String brand, Area activityArea, Date creationDate, int employeesNb,
			Long capital, int nbDeclaredSinistersC, boolean archived, Log logClientC) {
		super();
		this.idClientC = idClientC;
		this.brand = brand;
		this.activityArea = activityArea;
		this.creationDate = creationDate;
		this.employeesNb = employeesNb;
		this.capital = capital;
		this.nbDeclaredSinistersC = nbDeclaredSinistersC;
		this.archived = archived;
		this.logClientC = logClientC;
	}

	public CategoryClient getCategoryC() {
		return categoryC;
	}

	public void setCategoryC(CategoryClient categoryC) {
		this.categoryC = categoryC;
	}

	public String getMailC() {
		return MailC;
	}

	public void setMailC(String mailC) {
		MailC = mailC;
	}
	

}
