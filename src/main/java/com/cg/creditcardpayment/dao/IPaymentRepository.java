package com.cg.creditcardpayment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.creditcardpayment.entity.PaymentEntity;

@Repository
public interface IPaymentRepository extends JpaRepository<PaymentEntity, Long>{
	
	
}
