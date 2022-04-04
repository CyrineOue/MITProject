package tn.MITProject.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Product")
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) //
	@Column(name="IDProduct")
	private Long IDProduct;
	@Enumerated(EnumType.STRING)
	private Type TypeProduit;
	private String Nom;
	private String Description;
	
	@OneToMany (mappedBy = "coproduct")
	private Set<Contract> contracts;
	@ManyToMany
	private Set<Log> user;
	
	
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
	public Set<Log> getUser() {
		return user;
	}
	public void setUser(Set<Log> user) {
		this.user = user;
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