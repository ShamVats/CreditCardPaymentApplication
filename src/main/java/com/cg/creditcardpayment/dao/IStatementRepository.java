package com.cg.creditcardpayment.dao;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.creditcardpayment.entity.StatementEntity;

@Repository
public interface IStatementRepository extends JpaRepository<StatementEntity, Long>{
	
	StatementEntity findByBillDate(LocalDate billDate);
	
}
