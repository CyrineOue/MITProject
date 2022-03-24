package tn.MITProject.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Expert")
public class Expert implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long idExpert;
	private String name;
	private String lastName;
	private long phoneNb;
	private String expertiseField;
	
	@OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
	private Log logExpert;
	@OneToMany (mappedBy = "expert")
	private Set<Sinister> sinisters; 
	
	public long getIdExpert() {
		return idExpert;
	}
	public void setIdExpert(long idExpert) {
		this.idExpert = idExpert;
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
	public Log getLogExpert() {
		return logExpert;
	}
	public void setLogExpert(Log logExpert) {
		this.logExpert = logExpert;
	}
	public Set<Sinister> getSinisters() {
		return sinisters;
	}
	public void setSinisters(Set<Sinister> sinisters) {
		this.sinisters = sinisters;
	}
	public Expert(long idExpert, String name, String lastName,  long phoneNb,
			String expertiseField) {
		super();
		this.idExpert = idExpert;
		this.name = name;
		this.lastName = lastName;
		this.phoneNb = phoneNb;
		this.expertiseField = expertiseField;
	}
	public Expert() {
		super();
		// TODO Auto-generated constructor stub
	}
	



	
	

}
