package tn.MITProject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.MITProject.entities.Contract;
import tn.MITProject.repositories.ContractRepository;
import tn.MITProject.repositories.PaymentRepository;

@Service
public class ContractServiceImpl implements ContractService {
	@Autowired
	ContractRepository contractRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Override
	public List<Contract> retrieveAllContracts() {
		
		return (List<Contract>) contractRepository.findAll();
	}
//Restriction pour que ça soit seulement l'agent qui puisse ajouter le contrat?
	@Transactional
	@Override
	public Contract addContract(Contract c) {
		contractRepository.save(c);
		return null;
		/* prodrepository.save(c.getProduit());
		 * return contractRepository.save(c);
		 */
	}

	@Override
	public void deleteContract(Long id) {
		contractRepository.deleteById(id);
		
	}

	@Override
	public Contract updateContract(Contract c) {
	contractRepository.save(c);
		return c;
	}

	@Override
	public Contract retrieveContract(Long id) {
		return contractRepository.findById(id).orElse(null);
		
	}
	
	@Override
	public float EvaluateContractsNb(Long idClient) {
		int contractsNb= contractRepository.CountContracts(idClient);
		if (contractsNb >5 ) {
			return 1;
		}
			
		else { 
			if (contractsNb>3)
				return 0.5f;
		}
		return 0;
	}
	@Override
	public float EvaluateClaimsAmount(Long idClient) {
		 
		float rapport =contractRepository.TotalRefundAmount(idClient)/ contractRepository.TotalCeillingAmount(idClient);
		if (rapport<0.25)
			return 1;
		else {
			if (rapport <0.5)
				return 0.5f;
		}
		return 0;
	}
	
	}
	
	
