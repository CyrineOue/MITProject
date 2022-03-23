package tn.MITProject.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	@Column(name="type_produit")
	@Enumerated(EnumType.STRING)
	private Type TypeProduit;
	private String Nom;
	private String Description;
	
	
	@ManyToOne
	private Agent agent;
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
	public Agent getAgent() {
		return agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
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
	}
	
	@Override
	public String toString() {
		return "Product [IDProduct=" + IDProduct + ", TypeProduit=" + TypeProduit + ", Nom=" + Nom + ", Description="
				+ Description + ", agent=" + agent + ", contracts=" + contracts + ", particularClient="
				+ particularClient + ", companyClient=" + companyClient + "]";
	}
	
	

	
}
