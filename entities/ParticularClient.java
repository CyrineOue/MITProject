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

@Entity 
@Table(name="ParticularClient")
public class ParticularClient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idClientP")
	private long idClientP ;
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
	private int nbDeclaredSinistersP;
	private boolean archived=false;
	private final LocalDate sbuscriptionDate = LocalDate.now();
	private CategoryClient CategoryP;
	private float ScoreP;
	@OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
	private Log logClientP;
	
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
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public int getNbDeclaredSinisters() {
		return nbDeclaredSinistersP;
	}
	public void setNbDeclaredSinisters(int nbDeclaredSinisters) {
		this.nbDeclaredSinistersP = nbDeclaredSinisters;
	}
	public long getIdClientP() {
		return idClientP;
	}
	public void setIdClientP(long idClientP) {
		this.idClientP = idClientP;
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
	
	public Area getHomeAddress() {
		return homeAddress;
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
	public boolean isArchived() {
		return archived;
	}
	public void setArchived(boolean archived) {
		this.archived = archived;
	}
	public LocalDate getSbuscriptionDate() {
		return sbuscriptionDate;
	}
	public ParticularClient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ParticularClient(long idClientP, String firstName, String lastName, String profession, long cin,
			Date birthDate, Gender gender, Area homeAddress, Long phoneNo, int nbDeclaredSinistersP, boolean archived,
			Log logClientP) {
		super();
		this.idClientP = idClientP;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profession = profession;
		this.cin = cin;
		this.birthDate = birthDate;
		this.gender = gender;
		this.homeAddress = homeAddress;
		this.phoneNo = phoneNo;
		this.nbDeclaredSinistersP = nbDeclaredSinistersP;
		this.archived = archived;
		this.logClientP = logClientP;
	}
	public CategoryClient getCategoryP() {
		return CategoryP;
	}
	public void setCategoryP(CategoryClient categoryP) {
		CategoryP = categoryP;
	}
	public float getScoreP() {
		return ScoreP;
	}
	public void setScoreP(float scoreP) {
		ScoreP = scoreP;
	}
	

}
