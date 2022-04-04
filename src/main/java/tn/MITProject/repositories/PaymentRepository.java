package tn.MITProject.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.MITProject.entities.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {
	
	@Query("SELECT p FROM Payment p WHERE p.copayment.IDContract= :ContractId and p.Status=false")
	List<Payment> getPaymentByContrat(@Param("ContractId") Long contractid);
	
	@Query("SELECT SUM(PaidPremium) FROM Payment p WHERE p.copayment.IDContract= :IDContract and p.Status=true "
			+ "and p.PaymentDate between :startDate and :endDate ")
	double getPaidPremium(@Param("IDContract") Long IDContract,@Param("startDate") Date startDate,@Param("endDate") Date endDate);

}
