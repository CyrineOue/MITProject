package tn.MITProject.entities;

import java.io.Serializable;
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
	@Enumerated(EnumType.STRING)
	private Gender gender; 
	private String homeAddress;
	private int phoneNo;
	private int nbDeclaredSinistersP;
	
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
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public int getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
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
	public ParticularClient() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
