package tn.MITProject.Service;

import java.util.List;

import tn.MITProject.entities.ParticularClient;



public interface ParticularClientService {
	List<ParticularClient> retrieveAllParticularClients();

	ParticularClient addParticularClient (ParticularClient cc);

	void removeParticularClient (Long id);

	ParticularClient updateParticularClient (ParticularClient pc  );

	ParticularClient retrieveParticularClient (Long id);
	
	float EvaluateSeniority(Long idClient);
	
	float EvaluateArea (Long idClient);
	
	float scoreParticularClient(Long idClient);
	
	int CategoriyParticularClient (Long idClient );
}
