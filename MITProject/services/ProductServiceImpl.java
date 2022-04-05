package tn.MITProject.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.Admin;
import tn.MITProject.entities.Contract;
import tn.MITProject.entities.Log;
import tn.MITProject.entities.Product;
import tn.MITProject.entities.Type;
import tn.MITProject.repositories.ContractRepository;
import tn.MITProject.repositories.LogRepository;
import tn.MITProject.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	
	@Autowired
	ProductRepository productrepository;
	@Autowired
	LogRepository logrepository;
	LogService logservice;
	@Autowired
	ContractRepository contractrepository;
	ContractService contractService;
	AdminService adminservice;

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
	
	@Override
	public List<Product> findProductByType(Type TypeProduit) {
		List<Product> listProductByType =  productrepository.findProductByType(TypeProduit);
		return listProductByType;
	}

	
	@Override
	public int countContractByProduct(Long idprod) {
		List<Contract> listContractByProduct = productrepository.countContractByProduct(idprod);
		int  countContractByProduct = listContractByProduct.size();
		return countContractByProduct;
	}
	
	@Override
	public Product BestSellerProduct() {
		
		List<Contract> contracts = (List<Contract>) contractrepository.findAll();
		ArrayList <Long> list = new ArrayList<Long>();
		
		for(Contract c  :contracts)
		{	
			 list.add(c.getCoproduct().getIDProduct());
		}
		
		Long x = MaxRepeatingElement(list);
		
		List<Product> products = (List<Product>) productrepository.findAll();
		
		for(Product p  :products)
		{	
			 if(p.getIDProduct() == x)
				 return p;
		}
		
		
		
		return null ;
		
	}
	
	
	public Long MaxRepeatingElement(ArrayList<Long> list){
		
		 int maxCounter = 0;
		 Long element= (long) 0;
		 for (int i = 0; i <list.size() ; i++) {
		 int counter =1;
		 for (int j = i+1; j <list.size() ; j++) {
		 if(list.get(i)==list.get(j)){
		 counter++;
		 }
		 }
		 if(maxCounter<counter){
		 maxCounter=counter;
		 element = list.get(i);
		 }
		  }
		 return element;
		 //System.out.println("Element repeating maximum no of times: " + element + ", maximum count: " + maxCounter);
		 }
	
	
	@Override
	public Product WorstSellerProduct() {
		
		List<Contract> contracts = (List<Contract>) contractrepository.findAll();
		ArrayList <Long> list = new ArrayList<Long>();
		
		for(Contract c  :contracts)
		{	
			 list.add(c.getCoproduct().getIDProduct());
		}
		
		Long x = MinRepeatingElement(list);
		
		List<Product> products = (List<Product>) productrepository.findAll();
		
		for(Product p  :products)
		{	
			 if(p.getIDProduct() == x)
				 return p;
		}
		
		
		
		return null ;
		
	}
	
	public Long MinRepeatingElement(ArrayList<Long> list)
    {
         
  
        int min_count = list.size()+1;
		Long res = (long) -1;
        int curr_count = 1;
         
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) == list.get(i-1))
                curr_count++;
            else {
                if (curr_count < min_count) {
                    min_count = curr_count;
                    res = list.get(i - 1);
                }
                curr_count = 1;
            }
        }
        if (curr_count < min_count)
        {
            min_count = curr_count;
            res = list.get(list.size() - 1);
        }
     
        return res;
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
		Contract c = new Contract();
		if(sizeProperty <= 15) {
			c.setNetPremium((float) (propertyValue * 0.0045));
			contractrepository.save(c);
			System.out.println("b");
			return propertyValue * 0.0035;
			
		}
			
		else if(sizeProperty >15 && sizeProperty <= 35) {
			c.setNetPremium((float) (propertyValue * 0.0045));
			contractrepository.save(c);
			System.out.println("b");
			return propertyValue * 0.0045;
		}
			
		else
			c.setNetPremium((float) (propertyValue * 0.0045));
			contractrepository.save(c);
			System.out.println("b");
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
