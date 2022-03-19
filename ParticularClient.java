package tn.MITProject.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table
public class ParticularClient {
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
	private Gender gender; 
	private String homeAddress;
	private int phoneNo;
	private String email;
	private int nbDeclaredSinistersP;
	
	@ManyToMany(mappedBy = "particularClient")
	private Set<Product> products;
	@OneToMany (mappedBy = "sinParticularClient")
	private Set<Sinister> sinisters;
	
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
	public ParticularClient() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}