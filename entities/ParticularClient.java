package tn.MITProject.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity 
@Table (name="ParticularClient")
public class ParticularClient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idClientP")
	private long idClientP ;
	private String passwordP ;
	private String firstName;
	private String lastName;
	private String profession ;
	private long cin;
	private Date birthDate;   
	@Enumerated (EnumType.STRING)
	private Gender gender; 
	@Enumerated (EnumType.STRING)
	private Area homeAddress;
	private Long phoneNo;
	private String email;
	private int nbDeclaredSinistersP;
	private boolean archived=false;
	private final LocalDate sbuscriptionDate = LocalDate.now();
	
	@OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
	private Log logClientP;
	
	@ManyToMany(mappedBy = "particularClient")
	private Set<Product> products;
	@OneToMany (mappedBy = "sinParticularClient")
	private Set<Sinister> sinisters;
	
	//Getters & Setters 
	public String getPassword() {
		return passwordP;
	}
	public void setPassword(String password) {
		this.passwordP = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public long getCin() {
		return cin;
	}
	public void setCin(long cin) {
		this.cin = cin;
	}
	public Date getBirthDate() {
		return this.birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Gender getGender() {
		return this.gender ;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Area getHomeAddress() {
		return this.homeAddress;
	}
	public void setHomeAddress(Area homeAddress) {
		this.homeAddress = homeAddress;
	}
	public Long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getNbDeclaredSinisters() {
		return nbDeclaredSinistersP;
	}
	public void setNbDeclaredSinisters(int nbDeclaredSinisters) {
		this.nbDeclaredSinistersP = nbDeclaredSinisters;
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
	public String getPasswordP() {
		return passwordP;
	}
	public void setPasswordP(String passwordP) {
		this.passwordP = passwordP;
	}
	public int getNbDeclaredSinistersP() {
		return nbDeclaredSinistersP;
	}
	public void setNbDeclaredSinistersP(int nbDeclaredSinistersP) {
		this.nbDeclaredSinistersP = nbDeclaredSinistersP;
	}
	public Log getLogClientP() {
		return logClientP;
	}
	public void setLogClientP(Log logClientP) {
		this.logClientP = logClientP;
	}
	
	//Constructors
	public ParticularClient() {
		
	}
	public ParticularClient( Long id, String firstName, String lastName,String passwordP, String profession,
			long cin, Date birthDate, Gender gender, Area homeAddress, Long phoneNo, String email,
			int nbDeclaredSinistersP) {

		
		this.idClientP=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passwordP = passwordP;
		this.profession = profession;
		this.cin = cin;
		this.birthDate = birthDate;
		this.gender = gender;
		this.homeAddress = homeAddress;
		this.phoneNo = phoneNo;
		this.email = email;
		this.nbDeclaredSinistersP = nbDeclaredSinistersP;
		
	}
	

	

}