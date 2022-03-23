package tn.MITProject.Service;

import java.util.List;

import tn.MITProject.entities.Product;
import tn.MITProject.entities.Type;

public interface ProductService {
	//Basic Crud 
	List<Product> retrieveAllProducts();

	Product addProduct (Product p);

	Product updateProduct (Product p);

	Product retrieveProduct (Long id);

	void removeProduct (Long id);
	
	//Advanced Methods 
	List<Product> getProductByAgent(Long id);
	
	public void assignedProductToParticularClient(Long IDProduct,Long idClientP );
	
	public void assignedProductToCompanyClient(Long IDProduct,Long idClientC );
	
	Long discountAmount(Long prime,Long perdiscount);
	
	int countProductByType(Type TypeProduit);
	
	List<Product> findProductByType(Type TypeProduit);
	
	
	//Primum Calculation 
	
	//AgricultureHailInsurance
	Double AHIPremimumCalculation(Double fieldValue, String location,Long fieldSize );
	
	//AgricultureLiveStockMoralityInsurance
	Double ALMIPremimumCalculation(Long liveStockNumber, Double liveStockValue, String type );
	
	//SmallBusinessFireInsurance
	Double SBFIPremimumCalculation(Double propertyValue, Long sizeProperty);
	
	//Bicycle&MotorcycleInsurance
	Double BMIPremimumCalculation(Double propertyValue, Integer insuredAge );
	
	
	
	
	//List<CompanyClient> getCompanyClientClientByProduct(Long id);

}
