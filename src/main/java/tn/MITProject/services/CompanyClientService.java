package tn.MITProject.services;

import java.util.List;

import tn.MITProject.entities.CompanyClient;

public interface CompanyClientService {
	
	List<CompanyClient> retrieveAllCompanyClients();

	CompanyClient addCompanyClient (CompanyClient c);

	void deleteCompanyClient (Long id);

	CompanyClient updateCompanyClient (CompanyClient c);

	CompanyClient retrieveCompanyClient (Long id);
	
	float EvaluateSeniority (Long idClient);
	
	float EvaluateCapital (Long idClient);
	
	float EvaluateEmployeesNb (Long idClient);
	
	float EvaluateArea (Long idClient);
	
	float scoreCompanyClient(Long id);
	
	int CategoriyCompanyClient(Long idClient); 
	
	

}
