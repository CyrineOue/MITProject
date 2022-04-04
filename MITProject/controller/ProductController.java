package tn.MITProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.MITProject.entities.Product;
import tn.MITProject.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productservice;
	
	       // http://localhost:8081/mit/product/retrieve-all-products
			@GetMapping("/retrieve-all-products")
			@ResponseBody
			public List<Product> getProducts() {
			List<Product> listProducts = productservice.retrieveAllProducts();
			return listProducts;
			}	
			
			
			// http://localhost:8081/mit/product/retrieve-product/2
			@GetMapping("/retrieve-product/{product-id}")
			@ResponseBody
			public Product retrieveProduct(@PathVariable("product-id") Long IDProduct) {
			return productservice.retrieveProduct(IDProduct);
			}
			
			
			// http://localhost:8081/mit/product/add-product
			@PostMapping("/add-product")
			@ResponseBody
			public Product addProduct(@RequestBody Product p)
			{
				Product product = productservice.addProduct(p);
				return product;
			}
			
			
			// http://localhost:8081/mit/product/remove-product/{product-id}
			@DeleteMapping("/remove-product/{product-id}")
			@ResponseBody
			public void removeProduct(@PathVariable("product-id") Long productId) {
				productservice.removeProduct(productId);
			}
			
			
			// http://localhost:8081/mit/product/update-product
			@PutMapping("/update-product")
			@ResponseBody
			public Product updateProduct(@RequestBody Product product) {
			return productservice.updateProduct(product);
			}
	       // http://localhost:8081/mit/product/retrieve-products-by-agent/1
			@GetMapping("/retrieve-products-by-agent/{agent-id}")
			@ResponseBody
			public List<Product> getProductsbyagent(@PathVariable("agent-id") Long agentId) {
			List<Product> listProducts = productservice.getProductByAgent(agentId);
			return listProducts;
			}
			
			//http://localhost:8081/mit/product/assignproducttocompanyclient
			@PutMapping("/assignproducttocompanyclient/{product-id}/{id-clientc}")
			@ResponseBody
			public void assignProductToClientComapny(@PathVariable("product-id") Long IDProduct,@PathVariable("id-clientc") Long idClientC) 
			{
			 productservice.assignedProductToCompanyClient(IDProduct, idClientC);
			}
			
			//http://localhost:8081/mit/product/assignproducttoparticularclient
			@PutMapping("/assignproducttoparticularclient/{product-id}/{id-clientp}")
			@ResponseBody
			public void assignProductToClientParticular(@PathVariable("product-id") Long IDProduct,@PathVariable("id-clientp") Long idClientP) 
			{
			productservice.assignedProductToParticularClient(IDProduct, idClientP);
			}

}
