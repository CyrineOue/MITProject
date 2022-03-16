package tn.MITProject.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table
public class CompanyClient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idClientC")
	private long idClientC;
	private String brand;
	private String passwordC;
	@Enumerated
	private Area activityArea;
	private Date creationDate;
	private String email;
	private int employeesNb ;
	private Long capital;
	private int nbDeclaredSinistersC;
	private boolean archived=false;
	private final LocalDate sbuscriptionDate = LocalDate.now();
	@ManyToMany (mappedBy = "companyClient")
	private Set<Product> products;
	@OneToMany (mappedBy = "sinCompanyClient")
	private Set<Sinister> sinisters;
	
	//Getters & Setters 
	public String getPasswordC() {
		return passwordC;
	}
	public void setPasswordC(String passwordC) {
		this.passwordC = passwordC;
	}
	public Area getArea() {
		return activityArea;
	}
	public void setArea(Area activityArea) {
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

	public Long getCapital() {
		return capital;
	}
	public void setCapital(Long capital) {
		this.capital = capital;
	}
	
	//Constructors
	public CompanyClient() {

	}
	public CompanyClient( String passwordC, Area activityArea, String brand, Date creationDate,
			 String email, int employeesNb, Long capital, int nbDeclaredSinistersC) {	
		this.brand = brand;
		this.passwordC = passwordC;
		this.activityArea = activityArea;
		
		this.creationDate = creationDate;

		this.email = email;
		this.employeesNb = employeesNb;
		this.capital = capital;
		this.nbDeclaredSinistersC = nbDeclaredSinistersC;

	}
	public boolean isArchived() {
		return archived;
	}
	public void setArchived(boolean archived) {
		this.archived = archived;
	}
	public LocalDate getSbuscriptionDate() {
		return sbuscriptionDate;
	}


}