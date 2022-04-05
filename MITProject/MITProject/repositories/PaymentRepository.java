package tn.MITProject.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Contract;
import tn.MITProject.entities.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long>{
	//@Query("SELECT p FROM Payment p WHERE p.copayment.IDContract= :ContractId and p.Status=false")
	//List<Payment> getPaymentByContrat(@Param("ContractId") Long contractid);
	
	
	@Query("SELECT p FROM Payment p WHERE p.copayment.IDContract= :IDContract")
	List<Payment> retrievePaymentByContract(@Param("IDContract") Long IDContract);
	
	
	@Query("SELECT SUM(p.PaidPremium) FROM Payment p join Contract c on p.copayment.IDContract=c.IDContract "
			+ "WHERE p.PaymentDate between :startDate and :endDate and p.Status=true ")
	float RemainigAmountbetweentwodates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
