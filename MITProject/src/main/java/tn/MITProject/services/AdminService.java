package tn.MITProject.services;

import java.util.List;
import tn.MITProject.entities.Admin;

public interface AdminService {

	List<Admin> retrieveAllAdmins();

	Admin addAdmin (Admin a);

	void deleteAdmin (Long id);

	Admin updateAdmin (Admin a);

	Admin retrieveAdmin (Long id);
	
	Admin retrieveConnectedAdmin();

}
