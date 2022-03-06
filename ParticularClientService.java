package tn.MITProject.services;

import java.util.List;

import tn.MITProject.entities.ParticularClient;

public interface ParticularClientService {
	List<ParticularClient> retrieveAllParticularClients();

	ParticularClient addStock(ParticularClient pc);

	void deleteParticularClient (Long id);

	ParticularClient updateParticularClient (ParticularClient pc);

	ParticularClient retrieveParticularClient (Long id);
}
