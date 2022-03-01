package tn.MITProject.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Expert {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long idExpert;
	private String password;
	private String name;
	private String lastName;
	private String email;
	private long phoneNb;
	private String expertiseField;
	public long getIdExpert() {
		return idExpert;
	}
	public void setIdExpert(long idExpert) {
		this.idExpert = idExpert;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhoneNb() {
		return phoneNb;
	}
	public void setPhoneNb(long phoneNb) {
		this.phoneNb = phoneNb;
	}
	public String getExpertiseField() {
		return expertiseField;
	}
	public void setExpertiseField(String expertiseField) {
		this.expertiseField = expertiseField;
	}
	public Expert(long idExpert, String password, String name, String lastName, String email, long phoneNb,
			String expertiseField) {
		super();
		this.idExpert = idExpert;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.phoneNb = phoneNb;
		this.expertiseField = expertiseField;
	}
	public Expert() {
		super();
		// TODO Auto-generated constructor stub
	}
	



	
	

}
