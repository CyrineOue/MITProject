package tn.MITProject.services;

import java.util.List;

import tn.MITProject.entities.CompanyClient;

public interface CompanyClientService {
	
	List<CompanyClient> retrieveAllCompanyClients();

	CompanyClient addCompanyClient (CompanyClient c);

	void deleteCompanyClient (Long id);

	CompanyClient updateCompanyClient (CompanyClient c);

	CompanyClient retrieveCompanyClient (Long id);

}
