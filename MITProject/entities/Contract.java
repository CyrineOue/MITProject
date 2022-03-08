package tn.MITProject.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "Contract")
public class Contract {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="IDContract")
	private Long IDContract; 
	private int IDClient;
	@Temporal (TemporalType.DATE)
	private Date CreationDate;
	@Temporal (TemporalType.DATE)
	private Date StartDate;
	@Temporal (TemporalType.DATE)
	private Date EndDate;
	private float NetPremium;
	private float TTCPremium;
	private float CellingAmount;
	@ManyToOne
	private Product coproduct;
	@OneToOne
	private Payment payment;
	public Long getIDContract() {
		return IDContract;
	}
	public void setIDContract(Long iDContract) {
		IDContract = iDContract;
	}
	public int getIDClient() {
		return IDClient;
	}
	public void setIDClient(int iDClient) {
		IDClient = iDClient;
	}
	public Date getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(Date creationDate) {
		CreationDate = creationDate;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	public float getNetPremium() {
		return NetPremium;
	}
	public void setNetPremium(float netPremium) {
		NetPremium = netPremium;
	}
	public float getTTCPremium() {
		return TTCPremium;
	}
	public void setTTCPremium(float tTCPremium) {
		TTCPremium = tTCPremium;
	}
	public float getCellingAmount() {
		return CellingAmount;
	}
	public void setCellingAmount(float cellingAmount) {
		CellingAmount = cellingAmount;
	}
	public Contract() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}