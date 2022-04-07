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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Report")
public class Report implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idReport")
	private long idReport;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape=Shape.STRING, pattern="dd-MM-yyyy")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape=Shape.STRING, pattern="dd-MM-yyyy")
	private Date endDate;
	private long nbContracts;
	private long nbSinisters;
	private double fixedCharges;
	private double variableCharges;
	private double incomeTaxRate;
	private double turnover;
	private double netIncome;
	private double netIncomeMargin;
	private String monthCompanyClient;
	private String monthParticularClient;
    
    @ManyToOne
    @JsonIgnore
	private Admin admin;
	public long getIdReport() {
		return idReport;
	}
	public void setIdReport(long idReport) {
		this.idReport = idReport;
	}
	public long getNbContracts() {
		return nbContracts;
	}
	public void setNbContracts(long nbContracts) {
		this.nbContracts = nbContracts;
	}
	public long getNbSinisters() {
		return nbSinisters;
	}
	public void setNbSinisters(long nbSinisters) {
		this.nbSinisters = nbSinisters;
	}
	
	public Report(long idReport, long nbContracts, long nbSinisters) {
		super();
		this.idReport = idReport;
		this.nbContracts = nbContracts;
		this.nbSinisters = nbSinisters;
		
	}
	public double getFixedCharges() {
		return fixedCharges;
	}
	public void setFixedCharges(double fixedCharges) {
		this.fixedCharges = fixedCharges;
	}
	public double getVariableCharges() {
		return variableCharges;
	}
	public void setVariableCharges(double variableCharges) {
		this.variableCharges = variableCharges;
	}
	public double getIncomeTaxRate() {
		return incomeTaxRate;
	}
	public void setIncomeTaxRate(double incomeTaxeRate) {
		this.incomeTaxRate = incomeTaxeRate;
	}
	public double getNetIncome() {
		return netIncome;
	}
	public void setNetIncome(double netIncome) {
		this.netIncome = netIncome;
	}
	public double getTurnover() {
		return turnover;
	}
	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMonthCompanyClient() {
		return monthCompanyClient;
	}
	public void setMonthCompanyClient(String monthCompanyClient) {
		this.monthCompanyClient = monthCompanyClient;
	}
	public String getMonthParticularClient() {
		return monthParticularClient;
	}
	public void setMonthParticularClient(String monthParticularClient) {
		this.monthParticularClient = monthParticularClient;
	}
	public double getNetIncomeMargin() {
		return netIncomeMargin;
	}
	public void setNetIncomeMargin(double netIncomeMargin) {
		this.netIncomeMargin = netIncomeMargin;
	}
	
    
    
}
