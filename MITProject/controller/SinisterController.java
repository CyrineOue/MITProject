package tn.MITProject.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import tn.MITProject.entities.Sinister;
import tn.MITProject.services.SinisterService;
import tn.esprit.spring.entities.Reclamation;

@RestController
@RequestMapping("/sinister")
public class SinisterController {
	
	@Autowired
	SinisterService sinisterService;
	
    // http://localhost:8081/MITProject/sinister/retrieve-all-sinisters
	@GetMapping("/retrieve-all-sinisters")
	@ResponseBody
	public List<Sinister> getSinisters() {
	List<Sinister> listSinisters = sinisterService.retrieveAllSinisters();
	return listSinisters;
	}
		
	// http://localhost:8081/MITProject/sinister/add-sinister
	@PostMapping("/add-sinister")
	@ResponseBody
	public Sinister addSinister(@RequestBody Sinister s)
	{
	Sinister sinister = sinisterService.addSinister(s);
	return sinister;
	}
	
	// http://localhost:8081/Achat/sinister/remove-sinister/{sinister-id}
	@DeleteMapping("/remove-sinister/{sinister-id}")
	@ResponseBody
	public void removeSinister(@PathVariable("sinister-id") Long sinisterId) {
	sinisterService.deleteSinister(sinisterId);
	}
		
	// http://localhost:8081/MITProject/expert/modify-sinister
	@PutMapping("/modify-sinister")
	@ResponseBody
	public Sinister modifySinister(@RequestBody Sinister sinister) {
	return sinisterService.updateSinister(sinister);
	}
		
	//http://localhost:8081/MITProject/sinister/retrieve-sinister/{sinister-id}
	@GetMapping("/retrieve-sinister/{sinister-id}")
	@ResponseBody
    public Sinister  getSinistersById(@PathVariable("sinister-id") Long id) {
	Sinister s = sinisterService.retrieveSinisterById(id);
    return s ;
	}
		 	 
	//http://localhost:8081/MITProject/sinister/retrieve-sinister-ByCompanyClientId/{company-client-id}
	@GetMapping("/retrieve-sinister-ByCompanyClient/{company-client-id}")
	@ResponseBody
	public List<Sinister>  getSinisterByCompanyClient(@PathVariable("company-client-id") Long companyClientId) {    
	List<Sinister> list = sinisterService.getSinisterByCompanyClient( companyClientId  ) ;
	return list ; 
    }
	
	//http://localhost:8081/MITProject/sinister/retrieve-sinister-ByExpertId/{expert-id}
	@GetMapping("/retrieve-sinister-ByExpert/{expert-id}")
	@ResponseBody
	public List<Sinister>  getSinisterByExpert(@PathVariable("expert-id") Long expertId) {    
	List<Sinister> list = sinisterService.getSinisterByExpert( expertId  ) ;	 	
	return list ; 
	}
		 
	//http://localhost:8081/MITProject/sinister/retrieve-sinister-ByParticularClient/{particular-client-id}
	@GetMapping("/retrieve-sinister-ByParticularClient/{particular-client-id}")
	@ResponseBody
	public List<Sinister>  getSinisterByParticularClient(@PathVariable("particular-client-id") Long particularClientId) {    
	List<Sinister> list = sinisterService.getSinisterByParticularClient( particularClientId  ) ;
	return list ; 
    }
	
	//http://localhost:8081/MITProject/sinister/retrieve-sinister-ByParticularClient/{contract-id}
	@GetMapping("/retrieve-sinister-ByContract/{contract-id}")
	@ResponseBody
	public List<Sinister>  retrieveSinisterByContract(@PathVariable("contract-id") Long contractId) {    
	List<Sinister> list = sinisterService.retrieveSinisterByContract( contractId  ) ;
	return list ; 
    }
		
	//http://localhost:8081/MITProject/sinister/retrieve-sinister-ByDeclarationDate/2022-03-10/2022-03-18
	@GetMapping("/retrieve-sinister-ByDeclarationDate/{from}/{to}")
	@ResponseBody
	public List<Sinister>  retrieveSinisterByDeclarationDate(@PathVariable("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,@PathVariable("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to) {    
	List<Sinister> list = sinisterService.retrieveSinisterByDeclarationDate( from , to ) ;
	return list ; 
    }
	
	//http://localhost:8081/MITProject/sinister/retrieve-sinister-BySinisterDate/2022-03-10/2022-03-18
	@GetMapping("/retrieve-sinister-BySinisterDate/{from}/{to}")
	@ResponseBody
	public List<Sinister>  retrieveSinisterBySinisterDate(@PathVariable("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,@PathVariable("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to) {    
	List<Sinister> list = sinisterService.retrieveSinisterBySinisterDate( from , to ) ;
	return list ; 
    }
				 
	//http://localhost:8081/MITProject/sinister/checkSinisterDelay/{sinister-id}
	@GetMapping("/checkSinisterDelay/{sinister-id}")
	@ResponseBody
	public String  checkSinister(@PathVariable("sinister-id") Long sinisterId) {
	return sinisterService.checkSinisterDelay(sinisterId) ;
    }
	
	//http://localhost:8081/MITProject/sinister/treatSinistre/{sinister-id}
	@GetMapping("/treatSinistre/{sinister-id}")
	@ResponseBody
	public String  treatSinister(@PathVariable("sinister-id") Long sinisterId) throws Exception {
	return sinisterService.treatSinister(sinisterId) ;
    }
	
	@GetMapping("/AssignSinisterToExpert/{idSinister}/{idExpert}")
	@ResponseBody
	public Reclamation AssignSinisterToExpert(@PathVariable("idSinister") Long idSinister, @PathVariable ("idExpert") Long idExpert){
	      return  sinisterService.AssignSinisterToExpert(idSinister, idExpert);
	}
		 
		

}
