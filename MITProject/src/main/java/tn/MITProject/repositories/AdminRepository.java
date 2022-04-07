package tn.MITProject.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
	
	/*@Query("SELECT SUM(a.salary) FROM Admin a join a.logAdmin l where l.active=true")
	double getAdminSalaries();*/

	@Query("SELECT a FROM Admin a join a.logAdmin l where l.active=true and a.idAdmin =:idUser")
	Admin retrieveAdmin(@Param("idUser") Long idUser);
	@Query("SELECT a FROM Admin a join a.logAdmin l where l.email= :email")
	Admin retrieveAdminByEmail(@Param("email") String email);

}
