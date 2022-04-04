package tn.MITProject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Product;
import tn.MITProject.entities.Type;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT p FROM Product p join p.user u where u.idLog= :idLog")
	List<Product> retrieveproducts(@Param("idLog") Long idLog);
	//List<Product> findByUser_idLog(Long idLog);
	
	@Query("FROM Product p WHERE p.TypeProduit = :TypeProduit")
	 List<Product> findProductByType(@Param(value = "TypeProduit") Type TypeProduit);
	
	@Query("FROM Product p WHERE p.TypeProduit = :TypeProduit")
	 List<Product> countProductByType(@Param(value = "TypeProduit") Type TypeProduit);

}
