package tn.MITProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.MITProject.Service.ProductService;
import tn.MITProject.entities.Product;
import tn.MITProject.entities.Type;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productservice;
	
	
	// http://localhost:8089/SpringMVC/product/retrieve-all-products
		@GetMapping("/retrieve-all-products")
		@ResponseBody
		public List<Product> getProducts() {
		List<Product> listProducts = productservice.retrieveAllProducts();
		return listProducts;
		}	
		
		
		// http://localhost:8089/SpringMVC/product/retrieve-product/2
		@GetMapping("/retrieve-product/{product-id}")
		@ResponseBody
		public Product retrieveProduct(@PathVariable("product-id") Long IDProduct) {
		return productservice.retrieveProduct(IDProduct);
		}
		
		
		// http://localhost:8089/SpringMVC/product/add-product
		@PostMapping("/add-product")
		@ResponseBody
		public Product addProduct(@RequestBody Product p)
		{
			Product product = productservice.addProduct(p);
			return product;
		}
		
		
		// http://localhost:8089/SpringMVC/product/remove-product/{product-id}
		@DeleteMapping("/remove-product/{product-id}")
		@ResponseBody
		public void removeProduct(@PathVariable("product-id") Long productId) {
			productservice.removeProduct(productId);
		}
		
		
		// http://localhost:8089/SpringMVC/product/update-product
		@PutMapping("/update-product")
		@ResponseBody
		public Product updateProduct(@RequestBody Product product) {
		return productservice.updateProduct(product);
		}
		
		// http://localhost:8089/SpringMVC/product/retrieve-products-by-agent
		@GetMapping("/retrieve-products-by-agent/{agent-id}")
		@ResponseBody
		public List<Product> getProductsbyagent(@PathVariable("agent-id") Long agentId) {
		List<Product> listProducts = productservice.getProductByAgent(agentId);
		return listProducts;
		}
		
		/* http://localhost:8089/SpringMVC/product/retrieve-products-by-category
		
		@GetMapping("/retrieve-products-by-category/{category}")
		@ResponseBody
		public List<Product> getProductsbyCategory(@PathVariable("category") Type cat) {
		List<Product> listProducts = productservice.getProductByCategory(cat);
		return listProducts;
		}*/	
		
		
		//http://localhost:8089/SpringMVC/product/add-product-partclient
		//@PostMapping("/add-product-partclient/{product-id}/{id_clientc}")
		//@ResponseBody
		//public void addProductClientC(@PathVariable("product-id") Long productId , @PathVariable("id_clientc") Long idClientC) {
			//productservice.assignedProductToPClientCC(productId, idClientC);
		//}
		
		
		//http://localhost:8089/SpringMVC/product/assignproducttocompanyclient
		@GetMapping("/assignproducttocompanyclient/{product-id}/{id-clientc}")
		@ResponseBody
		public void assignProductToClientComapny(@PathVariable("product-id") Long IDProduct,@PathVariable("id-clientc") Long idClientC) 
		{
		 productservice.assignedProductToCompanyClient(IDProduct, idClientC);
		}
		
		//http://localhost:8089/SpringMVC/product/assignproducttoparticularclient
		@GetMapping("/assignproducttoparticularclient/{product-id}/{id-clientp}")
		@ResponseBody
		public void assignProductToClientParticular(@PathVariable("product-id") Long IDProduct,@PathVariable("id-clientp") Long idClientP) 
		{
		productservice.assignedProductToParticularClient(IDProduct, idClientP);
		}		
		
		
		//http://localhost:8089/SpringMVC/product/countproductbytype
		@GetMapping("/countproductbytype/{type}")	
		@ResponseBody
		public int countUserByRole(@PathVariable("type") String type) {
			
			System.out.println(Type.valueOf(type));
			//System.out.println(Type.valueOf("Customer"));
			int countProductByType= productservice.countProductByType(Type.valueOf(type));
			return countProductByType;
		}
		
		
		//http://localhost:8089/SpringMVC/product/findproductbytype
		@GetMapping("/findproductbytype/{type}")	
		@ResponseBody
		public List<Product> findUserByRole(@PathVariable("type") String type) {
			
			System.out.println(Type.valueOf(type));
			//System.out.println(Type.valueOf("Customer"));
			List<Product> listProduct = productservice.findProductByType(Type.valueOf(type));
			return listProduct;
		}
		
		
		//http://localhost:8089/SpringMVC/product/ahipremuimcalculation/10000.0/NW/5000
		@GetMapping("/ahipremuimcalculation/{fieldvalue}/{location}/{fieldsize}")
		@ResponseBody
		public Double ahipremuimcalculation(@PathVariable("fieldvalue") Double fieldValue,
											@PathVariable("location") String location,
											@PathVariable("fieldsize") Long fieldSize) {
			Double x = productservice.AHIPremimumCalculation(fieldValue, location, fieldSize);
			return x;
			
		}
		
		
		//http://localhost:8089/SpringMVC/product/almipremuimcalculation/100/1200.0/LS
		@GetMapping("/almipremuimcalculation/{livestocknumber}/{livestockvalue}/{type}")
		@ResponseBody
		public Double almipremuimcalculation(@PathVariable("livestocknumber") Long liveStockNumber,
											@PathVariable("livestockvalue") Double liveStockValue,
											@PathVariable("type") String type) {
			Double x = productservice.ALMIPremimumCalculation(liveStockNumber, liveStockValue, type);
			return x;
			
		}
		
		
		//http://localhost:8089/SpringMVC/product/sbfipremuimcalculation/15000/10
		@GetMapping("/sbfipremuimcalculation/{propertyvalue}/{sizeproperty}")
		@ResponseBody
		public Double sbfipremuimcalculation(@PathVariable("propertyvalue") Double propertyValue,
											 @PathVariable("sizeproperty") Long sizeProperty) {
			Double x = productservice.SBFIPremimumCalculation(propertyValue, sizeProperty);
			return x;
			
		}
		
		
		//http://localhost:8089/SpringMVC/product/bmipremuimcalculation/1500.0/22
				@GetMapping("/bmipremuimcalculation/{propertyvalue}/{insuredage}")
				@ResponseBody
				public Double bmipremuimcalculation(@PathVariable("propertyvalue") Double propertyValue,
													 @PathVariable("insuredage") Integer insuredAge) {
					Double x = productservice.BMIPremimumCalculation(propertyValue, insuredAge);
					return x;
					
				}
		
		
		
		
		
		
		
		
		
		
		
		
		
	
}
