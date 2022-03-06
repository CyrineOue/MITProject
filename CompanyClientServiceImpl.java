package tn.MITProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.CompanyClient;
import tn.MITProject.repositories.CompanyClientRepository;

@Service
public class CompanyClientServiceImpl implements CompanyClientService  {
	@Autowired
	CompanyClientRepository companyClient;
	@Override
	public List<CompanyClient> retrieveAllCompanyClients() {
		companyClient.findAll();
		return null;
	}

	@Override
	public CompanyClient addStock(CompanyClient cc) {
		companyClient.save(cc);
		return null;
	}

	@Override
	public void deleteCompanyClient(Long id) {
		companyClient.deleteById(id);
		
	}

	@Override
	public CompanyClient updateCompanyClient(CompanyClient cc) {
		companyClient.save(cc);
		return null;
	}

	@Override
	public CompanyClient retrieveCompanyClient(Long id) {
		companyClient.findById(id);
		return null;
	}
	

}
