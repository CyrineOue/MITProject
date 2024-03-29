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
