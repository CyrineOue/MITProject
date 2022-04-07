package tn.MITProject.entities;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="Admin")
public class Admin implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idAdmin")
	private long idAdmin;
	private String login;
	
	
	@OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
	private Log logAdmin;
	@OneToMany (mappedBy = "admin")
	@JsonIgnore
	private Set<Report> reports;
	
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
	public Log getLogAdmin() {
		return logAdmin;
	}
	public void setLogAdmin(Log logAdmin) {
		this.logAdmin = logAdmin;
	}
	public Admin() {
		
		// TODO Auto-generated constructor stub
	}
	public Admin(long idAdmin, String login) {
		super();
		this.idAdmin = idAdmin;
		this.login = login;
		
	}
	
	public Set<Report> getReports() {
		return reports;
	}
	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}
	
	

}
