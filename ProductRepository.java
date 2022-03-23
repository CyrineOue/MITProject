package tn.MITProject.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Product;
import tn.MITProject.entities.Type;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	
	//Select Products created by Particular Agent
	@Query("SELECT p FROM Product p WHERE p.agent.IDAgent= :IDAgent and p.agent.Active = true")
    List<Product> getProductsByAgent(@Param("IDAgent") Long IDAgent);
	
	@Query("FROM Product p WHERE p.TypeProduit = :TypeProduit")
	 List<Product> findProductByType(@Param(value = "TypeProduit") Type TypeProduit);
	
	@Query("FROM Product p WHERE p.TypeProduit = :TypeProduit")
	 List<Product> countProductByType(@Param(value = "TypeProduit") Type TypeProduit);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//insert product to client
	//@Modifying
	//@Query(value= "INSERT INTO product_company_client(products_idproduct,company_client_id_clientc) VALUES (:products_idproduct, :company_client_id_clientc)",nativeQuery = true)
	//void insertProductClientC(@Param("products_idproduct") Long products_idproduct , @Param("company_client_id_clientc") Long company_client_id_clientc);
	
	
	

}
