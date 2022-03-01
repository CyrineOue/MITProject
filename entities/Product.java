package tn.MITProject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Product {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) //
	@Column(name="IDProduct")
	private Type TypeProduit;
	private String Nom;
	private String Description;
	
	
	
	
	
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

	
}
