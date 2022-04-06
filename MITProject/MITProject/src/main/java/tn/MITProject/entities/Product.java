package tn.MITProject.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Product {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) //
	@Column(name="IDProduct")
	private Long IDProduct;
	private Type TypeProduit;
	private String Nom;
	private String Description;

	@OneToMany (mappedBy = "coproduct")
	private Set<Contract> contracts;
	@ManyToMany
	private Set<ParticularClient> particularClient;
	@ManyToMany
	private Set<CompanyClient> companyClient;
	
	
	public Type getTypeProduit() {
		return TypeProduit;
	}
	public void setTypeProduit(Type typeProduit) {
		TypeProduit = typeProduit;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	public Long getIDProduct() {
		return IDProduct;
	}
	public void setIDProduct(Long iDProduct) {
		IDProduct = iDProduct;
	}
	
	public Set<Contract> getContracts() {
		return contracts;
	}
	public void setContracts(Set<Contract> contracts) {
		this.contracts = contracts;
	}
	public Set<ParticularClient> getParticularClient() {
		return particularClient;
	}
	public void setParticularClient(Set<ParticularClient> particularClient) {
		this.particularClient = particularClient;
	}
	public Set<CompanyClient> getCompanyClient() {
		return companyClient;
	}
	public void setCompanyClient(Set<CompanyClient> companyClient) {
		this.companyClient = companyClient;
	}
	public Product(Type typeProduit, String nom, String description) {
		super();
		TypeProduit = typeProduit;
		Nom = nom;
		Description = description;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
