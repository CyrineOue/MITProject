package tn.MITProject.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private double salary;
	private String expertiseField;
	
	@OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
	@JsonIgnore
	private Log logExpert;
	
	
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
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	



	
	

}
