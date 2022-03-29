package tn.MITProject.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "Contract")
public class Contract implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="IDContract")
	private Long IDContract; 
	private Long IDClient;
	@Temporal (TemporalType.DATE)
	private Date CreationDate;
	@Temporal (TemporalType.DATE)
	private Date StartDate;
	@Temporal (TemporalType.DATE)
	private Date EndDate;
	private float NetPremium;
	private float TTCPremium;
	private float CeillingAmount;
	private int InstallmentsNB;
	@Enumerated(EnumType.STRING)
	private Status Costatus;
	@ManyToOne
	private Product coproduct;
	@OneToMany (mappedBy = "copayment")
	private Set<Payment> payments;
	public Long getIDContract() {
		return IDContract;
	}
	public void setIDContract(Long iDContract) {
		IDContract = iDContract;
	}
	public Long getIDClient() {
		return IDClient;
	}
	public void setIDClient(Long iDClient) {
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

	public Contract() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product getCoproduct() {
		return coproduct;
	}
	public void setCoproduct(Product coproduct) {
		this.coproduct = coproduct;
	}
	
	public Contract(Long iDContract, Long iDClient, Date creationDate, Date startDate, Date endDate, float netPremium,
			float tTCPremium, float cellingAmount, Product coproduct, Payment payment) {
		super();
		IDContract = iDContract;
		IDClient = iDClient;
		CreationDate = creationDate;
		StartDate = startDate;
		EndDate = endDate;
		NetPremium = netPremium;
		TTCPremium = tTCPremium;
		this.coproduct = coproduct;
		
	}
	public Set<Payment> getPayments() {
		return payments;
	}
	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}
	public Status getCostatus() {
		return Costatus;
	}
	public void setCostatus(Status costatus) {
		Costatus = costatus;
	}
	public float getCeillingAmount() {
		return CeillingAmount;
	}
	public void setCeillingAmount(float ceillingAmount) {
		CeillingAmount = ceillingAmount;
	}
	public int getInstallmentsNB() {
		return InstallmentsNB;
	}
	public void setInstallmentsNB(int installmentsNB) {
		InstallmentsNB = installmentsNB;
	}
	
}
