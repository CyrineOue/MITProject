package tn.MITProject.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long>{
	//@Query("SELECT p FROM Payment f WHERE p.Contracts.IDContract= :contractId and p.Status=false")
	//List<Payment> getPaymentByContrat(@Param("contractId") Long contractid);
	

}
