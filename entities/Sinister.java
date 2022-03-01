package tn.MITProject.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

public class Sinister {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long idSinister;
	private Type typeSinister;
	@Temporal(TemporalType.DATE)
	private Date dateDeclaration;
	private String expertJudgement;
	private long damageAmount;
	private Status statusSinister;
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


	
}
	
