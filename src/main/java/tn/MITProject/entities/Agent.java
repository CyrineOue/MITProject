package tn.MITProject.entities;

import java.io.Serializable;

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
@Table(name="Agent")
public class Agent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) //
	@Column(name="IDAgent")
	private Long IDAgent;
	private String Name;
	private String LastName;
	private Long PhoneNb;
	private Long ContractsNb;
	
	@OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
	private Log logAgent;
	
	
	public Long getIDAgent() {
		return IDAgent;
	}
	public void setIDAgent(Long iDAgent) {
		IDAgent = iDAgent;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	public Long getPhoneNb() {
		return PhoneNb;
	}
	public void setPhoneNb(Long phoneNb) {
		PhoneNb = phoneNb;
	}
	
	public Long getContractsNb() {
		return ContractsNb;
	}
	public void setContractsNb(Long contractsNb) {
		ContractsNb = contractsNb;
	}
	
	
	public Log getLogAgent() {
		return logAgent;
	}
	public void setLogAgent(Log logAgent) {
		this.logAgent = logAgent;
	}
	public Agent(Long iDAgent, String name, String lastName, Long phoneNb, Long contractsNb) {
		super();
		IDAgent = iDAgent;
		Name = name;
		LastName = lastName;
		PhoneNb = phoneNb;
		ContractsNb = contractsNb;
	}
	public Agent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	

}