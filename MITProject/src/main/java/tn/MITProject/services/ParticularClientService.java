package tn.MITProject.services;

import java.util.List;

import tn.MITProject.entities.ParticularClient;

public interface ParticularClientService {
	
	List<ParticularClient> retrieveAllParticularClients();

	ParticularClient addParticularClient (ParticularClient p);

	void deleteParticularClient (Long id);

	ParticularClient updateParticularClient (ParticularClient p);

	ParticularClient retrieveParticularClient (Long id);
	
    float EvaluateSeniority(Long idClient);
	
	float EvaluateArea (Long idClient);
	
	float scoreParticularClient(Long idClient);	
	
	float EvaluateClaimsAmount(Long idClient);

	void categoriseParticularClient();
}
