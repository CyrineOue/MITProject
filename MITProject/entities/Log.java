package tn.MITProject.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
@Table(name="Log")
public class Log implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idLog")
	private long idLog;
	private String password;
	private String email;
	private Boolean active=true;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToOne(mappedBy = "logAdmin")
	@JsonIgnore
	private Admin admin;
	
	@OneToOne(mappedBy = "logAgent")
	@JsonIgnore
	private Agent agent;
	
	@OneToOne(mappedBy = "logExpert")
	@JsonIgnore
	private Expert expert;
	
	@OneToOne(mappedBy = "logClientP")
	@JsonIgnore
	private ParticularClient particularClient;
	
	@OneToOne(mappedBy = "logClientC")
	@JsonIgnore
	private CompanyClient companyClient;
	
	@ManyToMany (mappedBy = "user")
	@JsonIgnore
	private Set<Product> products;
	
	@OneToMany (mappedBy = "logSinister")
	private Set<Sinister> sinisters; 

	public long getIdLog() {
		return idLog;
	}
	public void setIdLog(long idLog) {
		this.idLog = idLog;
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
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Agent getAgent() {
		return agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	public Expert getExpert() {
		return expert;
	}
	public void setExpert(Expert expert) {
		this.expert = expert;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public ParticularClient getParticularClient() {
		return particularClient;
	}
	public void setParticularClient(ParticularClient particularClient) {
		this.particularClient = particularClient;
	}
	public CompanyClient getCompanyClient() {
		return companyClient;
	}
	public void setCompanyClient(CompanyClient companyClient) {
		this.companyClient = companyClient;
	}
	public Set<Sinister> getSinisters() {
		return sinisters;
	}
	public void setSinisters(Set<Sinister> sinisters) {
		this.sinisters = sinisters;
	}
	public Log() {
		
		// TODO Auto-generated constructor stub
	}
	public Log(long idLog, String password, String email, Boolean active, Role role, Admin admin) {
		super();
		this.idLog = idLog;
		this.password = password;
		this.email = email;
		this.active = active;
		this.role = role;
		this.admin = admin;
	}
	
	
	
	
	

}
