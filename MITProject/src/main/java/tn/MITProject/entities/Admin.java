package tn.MITProject.entities;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity

public class Admin {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idAdmin")
	private long idAdmin;
	private String login;
	private String password;
	private String email;
	@OneToMany (mappedBy = "readmin")
	private Set<Report> reports;
	@OneToMany (mappedBy = "agadmin")
	private Set<Agent> agents;
	
	public long getIdAdmin() {
		return idAdmin;
	}
	public void setIdAdmin(long idAdmin) {
		this.idAdmin = idAdmin;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Admin(long idAdmin, String login, String password, String email) {
		super();
		this.idAdmin = idAdmin;
		this.login = login;
		this.password = password;
		this.email = email;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}