package tn.MITProject.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Sinister")
public class Sinister implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long idSinister;
	@Enumerated(EnumType.STRING)
	private Type typeSinister;
	@Temporal(TemporalType.DATE)
	private Date dateDeclaration;
	private String expertJudgement;
	private long damageAmount;
	@Enumerated(EnumType.STRING)
	private Status statusSinister;
	private String placement;
	@ManyToOne
	private CompanyClient sinCompanyClient;
	@ManyToOne
	private ParticularClient sinParticularClient;
	@ManyToOne
	private Expert expert;
	
	
	public long getIdSinister() {
		return idSinister;
	}
	public void setIdSinister(long idSinister) {
		this.idSinister = idSinister;
	}
	public Type getTypeSinister() {
		return typeSinister;
	}
	public void setTypeSinister(Type typeSinister) {
		this.typeSinister = typeSinister;
	}
	public Date getDateDeclaration() {
		return dateDeclaration;
	}
	public void setDateDeclaration(Date dateDeclaration) {
		this.dateDeclaration = dateDeclaration;
	}
	public String getExpertJudgement() {
		return expertJudgement;
	}
	public void setExpertJudgement(String expertJudgement) {
		this.expertJudgement = expertJudgement;
	}
	public long getDamageAmount() {
		return damageAmount;
	}
	public void setDamageAmount(long damageAmount) {
		this.damageAmount = damageAmount;
	}
	public Sinister(long idSinister, Type typeSinister, Date dateDeclaration, String expertJudgement,
			long damageAmount) {
		super();
		this.idSinister = idSinister;
		this.typeSinister = typeSinister;
		this.dateDeclaration = dateDeclaration;
		this.expertJudgement = expertJudgement;
		this.damageAmount = damageAmount;
	}
	public Sinister() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Status getStatusSinister() {
		return statusSinister;
	}
	public void setStatusSinister(Status statusSinister) {
		this.statusSinister = statusSinister;
	}
	public String getPlacement() {
		return placement;
	}
	public void setPlacement(String placement) {
		this.placement = placement;
	}


	
}
