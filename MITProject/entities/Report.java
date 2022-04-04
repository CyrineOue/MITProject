package tn.MITProject.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private long nbContracts;
	private long nbSinisters;
    private long CAmonth;
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
	public long getCAmonth() {
		return CAmonth;
	}
	public void setCAmonth(long cAmonth) {
		CAmonth = cAmonth;
	}
	public Report(long idReport, long nbContracts, long nbSinisters, long cAmonth) {
		super();
		this.idReport = idReport;
		this.nbContracts = nbContracts;
		this.nbSinisters = nbSinisters;
		CAmonth = cAmonth;
	}
    
    
}
