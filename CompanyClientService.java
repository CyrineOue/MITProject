package tn.MITProject.services;

import java.util.List;

import org.springframework.stereotype.Repository;

import tn.MITProject.entities.CompanyClient;
@Repository
public interface CompanyClientService {
	List<CompanyClient> retrieveAllCompanyClients();

	CompanyClient addStock(CompanyClient cc);

	void deleteCompanyClient (Long id);

	CompanyClient updateCompanyClient (CompanyClient cc);

	CompanyClient retrieveCompanyClient (Long id);

}
