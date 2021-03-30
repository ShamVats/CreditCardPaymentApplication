package com.cg.creditcardpayment.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.creditcardpayment.exception.CreditCardException;
import com.cg.creditcardpayment.exception.StatementException;
import com.cg.creditcardpayment.model.StatementModel;

public interface IStatementService {

	boolean existsById(Long statementId) throws StatementException;
	
	
	StatementModel add(StatementModel statement) throws StatementException;
	StatementModel save(StatementModel statement) throws StatementException;
	
	void deleteById(Long statementId) throws StatementException;
	
	StatementModel findById(Long statementId) throws StatementException;
	
	List<StatementModel> findAll();
	
	StatementModel findByBillDate(LocalDate billDate);
	

	StatementModel getBilledStatement(String cardNumber) throws CreditCardException, StatementException;
	StatementModel getUnBilledStatement(String cardNumber) throws CreditCardException;
	
	List<StatementModel> statementHistory(String cardNumber) throws CreditCardException;
}
