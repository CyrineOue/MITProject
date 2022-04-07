package tn.MITProject.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "Payment")
public class Payment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="IDPayment")
	private int IDPayment; //clé primaire
	private String Method;
	@Temporal (TemporalType.DATE)
	private Date PaymentDate;
	private float PaidPremium;
	private float RemainingPremium;
	private float RefundAmount;
	private boolean Status;

	@ManyToOne
	private Contract copayment;
	
	
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
	public Contract getCopayment() {
		return copayment;
	}
	public void setCopayment(Contract copayment) {
		this.copayment = copayment;
	} 
	
	
	
	
}
