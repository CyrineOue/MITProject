package tn.MITProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.Log;
import tn.MITProject.entities.Product;
import tn.MITProject.entities.Type;
import tn.MITProject.repositories.LogRepository;
import tn.MITProject.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productrepository;
	@Autowired
	LogRepository logrepository;

	@Override
	public List<Product> retrieveAllProducts() {
		return (List<Product>) productrepository.findAll() ;
	}

	@Override
	public Product addProduct(Product p) {
		productrepository.save(p);
		return p;
	}

	@Override
	public Product updateProduct(Product p) {
		productrepository.save(p);
		return p;
	}

	@Override
	public Product retrieveProduct(Long id) {
		productrepository.findById(id);
		return productrepository.findById(id).orElse(null);
	}

	@Override
	public void removeProduct(Long id) {
		productrepository.deleteById(id);
		
	}

	@Override
	public List<Product> getProductByAgent(Long id) {
		Long idLog= logrepository.findAgent(id);
		//return (List<Product>) productrepository.findByUser_idLog(idLog);
		return (List<Product>) productrepository.retrieveproducts(idLog);
	}

	@Override
	public void assignedProductToParticularClient(Long IDProduct, Long idClientP) {
        Product p =productrepository.findById(IDProduct).orElse(null);
        Long idlog = logrepository.findClientP(idClientP);	
        Log log = logrepository.findById(idlog).orElse(null);
		p.getUser().add(log);
		productrepository.save(p);
		
	}

	@Override
	public void assignedProductToCompanyClient(Long IDProduct, Long idClientC) {
        Product p =productrepository.findById(IDProduct).orElse(null);
        Long idlog = logrepository.findClientC(idClientC);	
        Log log = logrepository.findById(idlog).orElse(null);
		p.getUser().add(log);
		productrepository.save(p);
		
	}
	
	@Override
	public Long discountAmount(Long prime, Long perdiscount) {
		
		Long d = prime - (perdiscount*prime)/100;
		
		return d;
	}

	@Override
	public int countProductByType(Type TypeProduit) {
		List<Product>listProductByType =  productrepository.countProductByType(TypeProduit);
		 int countProductByType =  listProductByType.size();
		return countProductByType;	
	}
	//@Override
	//public void assignedProductToPClientCC(Long IDProduct, Long idClientC) {
	    //productrepository.insertProductClientC(IDProduct, idClientC);

	@Override
	public List<Product> findProductByType(Type TypeProduit) {
		List<Product> listProductByType =  productrepository.findProductByType(TypeProduit);
		return listProductByType;
	}

	
	@Override
	public Double AHIPremimumCalculation(Double fieldValue, String location ,Long fieldSize) {
		
		if( fieldSize <= 2000) {
				switch(location) {
					case "NW":
						return fieldValue * 0.0055;
					case "CW":
						return fieldValue * 0.0045;
					case "NE":
						return fieldValue * 0.0035;
					case "O" :
						return fieldValue * 0.0025;
				}
				
		}else if(fieldSize > 2000 && fieldSize <=8000) {
				switch(location) {
					case "NW":
						return fieldValue * 0.0065;
					case "CW":
						return fieldValue * 0.0055;
					case "NE":
						return fieldValue * 0.0045;
					case "O" :
						return fieldValue * 0.0035;	
		}
			
		}else {
				switch(location) {
				case "NW":
					return fieldValue * 0.0075;
				case "CW":
					return fieldValue * 0.0065;
				case "NE":
					return fieldValue * 0.0055;	
				case "O" :
					return fieldValue * 0.0045;	
			
				}
		
		}
		return 0.0;
	}

	@Override
	public Double ALMIPremimumCalculation(Long liveStockNumber, Double liveStockValue, String type) {
		switch(type) {
			case "LS":
				return liveStockValue * liveStockNumber * 0.0035;
			case"P":
				return liveStockValue * liveStockNumber * 0.0075;
			
		}
		return 0.0;
		
	}

	@Override
	public Double SBFIPremimumCalculation(Double propertyValue, Long sizeProperty) {
		if(sizeProperty <= 15)
			return propertyValue * 0.0035;
		else if(sizeProperty >15 && sizeProperty <= 35)
			return propertyValue * 0.0045;
		else
			return propertyValue * 0.0055;
	}

	@Override
	public Double BMIPremimumCalculation(Double propertyValue, Integer insuredAge) {
		if (insuredAge <25)
			return propertyValue * 0.0055;
		else if (insuredAge > 25 && insuredAge <=40)
			return propertyValue * 0.0045;
		else
			return propertyValue * 0.0035;
		
	}



}
