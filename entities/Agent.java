package tn.MITProject.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Agent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) //
	@Column(name="IDAgent")
	private Long IDAgent;
	private String Password;
	private String Name;
	private String LastName;
	private String Email;
	private Long PhoneNb;
	private Boolean Active;
	private Long ContactsNb;
	
	
	
	@ManyToOne
	private Admin agadmin;
	@OneToMany (mappedBy = "agent")
	private Set<Product> products;
	
	
	
	
	
	
	public Long getIDAgent() {
		return IDAgent;
	}
	public void setIDAgent(Long iDAgent) {
		IDAgent = iDAgent;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
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
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Long getPhoneNb() {
		return PhoneNb;
	}
	public void setPhoneNb(Long phoneNb) {
		PhoneNb = phoneNb;
	}
	public Boolean getActive() {
		return Active;
	}
	public void setActive(Boolean active) {
		Active = active;
	}
	public Long getContactsNb() {
		return ContactsNb;
	}
	public void setContactsNb(Long contactsNb) {
		ContactsNb = contactsNb;
	}
	
	
	public Agent(Long iDAgent, String password, String name, String lastName, String email, Long phoneNb,
			Boolean active, Long contactsNb) {
		super();
		IDAgent = iDAgent;
		Password = password;
		Name = name;
		LastName = lastName;
		Email = email;
		PhoneNb = phoneNb;
		Active = active;
		ContactsNb = contactsNb;
	}
	public Agent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	

}
