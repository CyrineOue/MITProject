package tn.MITProject.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "Payment")
public class Payment {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="IDPayment")
	private int IDPayment; //clé primaire
	private String Method;
	@Temporal (TemporalType.DATE)
	private Date PaymentDate;
	private int InstallmentsNB;
	private float PaidPremium;
	private float RemainingPremium;
	private float RefundAmount;
	private boolean Status;
	@OneToOne (mappedBy = "payment")
	private Contract contract; 
	
	
	public int getIDPayment() {
		return IDPayment;
	}
	public void setIDPayment(int iDPayment) {
		IDPayment = iDPayment;
	}
	public String getMethod() {
		return Method;
	}
	public void setMethod(String method) {
		Method = method;
	}
	public Date getPaymentDate() {
		return PaymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		PaymentDate = paymentDate;
	}
	public int getInstallmentsNB() {
		return InstallmentsNB;
	}
	public void setInstallmentsNB(int installmentsNB) {
		InstallmentsNB = installmentsNB;
	}
	public float getPaidPremium() {
		return PaidPremium;
	}
	public void setPaidPremium(float paidPremium) {
		PaidPremium = paidPremium;
	}
	public float getRemainingPremium() {
		return RemainingPremium;
	}
	public void setRemainingPremium(float remainingPremium) {
		RemainingPremium = remainingPremium;
	}
	public float getRefundAmount() {
		return RefundAmount;
	}
	public void setRefundAmount(float refundAmount) {
		RefundAmount = refundAmount;
	}
	public boolean isStatus() {
		return Status;
	}
	public void setStatus(boolean status) {
		Status = status;
	}
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}