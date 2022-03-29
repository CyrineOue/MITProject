package tn.MITProject.services;

import java.util.List;

import tn.MITProject.entities.Expert;

public interface ExpertService {

	List<Expert> retrieveAllExperts();

	Expert addExpert (Expert e);

	void deleteExpert (Long id);

	Expert updateExpert (Expert e);

	Expert retrieveExpert (Long id);
}
