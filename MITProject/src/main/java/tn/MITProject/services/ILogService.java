package tn.MITProject.services;

import tn.MITProject.entities.Log;

public interface ILogService {
	
	Log findLogByLogEmail(String email);
	void fireAgent(Long id);
	void fireExpert(Long id);

}
