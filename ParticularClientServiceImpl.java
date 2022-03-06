package tn.MITProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.MITProject.entities.ParticularClient;
import tn.MITProject.repositories.ParticularClientRepository;


@Service
public class ParticularClientServiceImpl implements ParticularClientService {
	@Autowired
	ParticularClientRepository particularClient;
	@Override
	public List<ParticularClient> retrieveAllParticularClients() {
		particularClient.findAll();
		return null;
	}

	@Override
	public ParticularClient addStock(ParticularClient pc) {
		particularClient.save(pc);
		return null;
	}

	@Override
	public void deleteParticularClient(Long id) {
		particularClient.deleteById(id);
		
	}

	@Override
	public ParticularClient updateParticularClient(ParticularClient pc) {
		particularClient.save(pc);
		return null;
	}

	@Override
	public ParticularClient retrieveParticularClient(Long id) {
		particularClient.findById(id);
		return null;
	}


}
