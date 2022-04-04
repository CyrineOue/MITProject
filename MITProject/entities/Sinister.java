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
	private Long idSinister;
	@Enumerated(EnumType.STRING)
	private Type typeSinister;
	@Temporal(TemporalType.DATE)
	private Date declarationDate;
	@Temporal (TemporalType.DATE)
	private Date indemnisationDate;
	@Temporal (TemporalType.DATE)
	private Date sinisterDate;
	private Integer declarationDelay=7;
	private String expertJudgement;
	private Float damageAmount;
	@Enumerated(EnumType.STRING)
	private Status sinisterStatus;
	private String sinisterLocation;
	private String sinisterDescription;
	private String causeOfRejection;
	private String isFraud;
	private Integer refundPercentage;
	private Float refundAmount;
	@ManyToOne
	private Log logSinister;
	
	public Long getIdSinister() {
		return idSinister;
	}
	public void setIdSinister(Long idSinister) {
		this.idSinister = idSinister;
	}
	public Type getTypeSinister() {
		return typeSinister;
	}
	public void setTypeSinister(Type typeSinister) {
		this.typeSinister = typeSinister;
	}
	public Date getDeclarationDate() {
		return declarationDate;
	}
	public void setDeclarationDate(Date declarationDate) {
		this.declarationDate = declarationDate;
	}
	public Date getIndemnisationDate() {
		return indemnisationDate;
	}
	public void setIndemnisationDate(Date indemnisationDate) {
		this.indemnisationDate = indemnisationDate;
	}
	public Date getSinisterDate() {
		return sinisterDate;
	}
	public void setSinisterDate(Date sinisterDate) {
		this.sinisterDate = sinisterDate;
	}
	public Integer getDeclarationDelay() {
		return declarationDelay;
	}
	public void setDeclarationDelay(Integer declarationDelay) {
		this.declarationDelay = declarationDelay;
	}
	public String getExpertJudgement() {
		return expertJudgement;
	}
	public void setExpertJudgement(String expertJudgement) {
		this.expertJudgement = expertJudgement;
	}
	public Float getDamageAmount() {
		return damageAmount;
	}
	public void setDamageAmount(Float damageAmount) {
		this.damageAmount = damageAmount;
	}
	public Status getSinisterStatus() {
		return sinisterStatus;
	}
	public void setSinisterStatus(Status sinisterStatus) {
		this.sinisterStatus = sinisterStatus;
	}
	public String getSinisterLocation() {
		return sinisterLocation;
	}
	public void setSinisterLocation(String sinisterLocation) {
		this.sinisterLocation = sinisterLocation;
	}
	public String getSinisterDescription() {
		return sinisterDescription;
	}
	public void setSinisterDescription(String sinisterDescription) {
		this.sinisterDescription = sinisterDescription;
	}
	public String getCauseOfRejection() {
		return causeOfRejection;
	}
	public void setCauseOfRejection(String causeOfRejection) {
		this.causeOfRejection = causeOfRejection;
	}
	public String getIsFraud() {
		return isFraud;
	}
	public void setIsFraud(String isFraud) {
		this.isFraud = isFraud;
	}
	public Integer getRefundPercentage() {
		return refundPercentage;
	}
	public void setRefundPercentage(Integer refundPercentage) {
		this.refundPercentage = refundPercentage;
	}
	public Log getLogSinister() {
		return logSinister;
	}
	public void setLogSinister(Log logSinister) {
		this.logSinister = logSinister;
	}
	public Sinister() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Float getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(Float refundAmount) {
		this.refundAmount = refundAmount;
	}
	public Sinister(Long idSinister, Type typeSinister, Date declarationDate, Date indemnisationDate, Date sinisterDate,
			Integer declarationDelay, String expertJudgement, Float damageAmount, Status sinisterStatus,
			String sinisterLocation, String sinisterDescription, String causeOfRejection, String isFraud,
			Integer refundPercentage, Float refundAmount) {
		super();
		this.idSinister = idSinister;
		this.typeSinister = typeSinister;
		this.declarationDate = declarationDate;
		this.indemnisationDate = indemnisationDate;
		this.sinisterDate = sinisterDate;
		this.declarationDelay = declarationDelay;
		this.expertJudgement = expertJudgement;
		this.damageAmount = damageAmount;
		this.sinisterStatus = sinisterStatus;
		this.sinisterLocation = sinisterLocation;
		this.sinisterDescription = sinisterDescription;
		this.causeOfRejection = causeOfRejection;
		this.isFraud = isFraud;
		this.refundPercentage = refundPercentage;
		this.refundAmount = refundAmount;
	}
	


	
}
