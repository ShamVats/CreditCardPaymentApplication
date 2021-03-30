package com.cg.creditcardpayment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.creditcardpayment.entity.TransactionEntity;

@Repository
public interface ITransactionRepository extends JpaRepository<TransactionEntity, Long>{
	

}
