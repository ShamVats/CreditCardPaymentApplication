package com.cg.creditcardpayment.service;

import java.util.List;

import com.cg.creditcardpayment.exception.CreditCardException;
import com.cg.creditcardpayment.exception.TransactionException;
import com.cg.creditcardpayment.model.TransactionModel;

public interface ITransactionService {

	boolean existsById(Long transactionId) throws TransactionException;

	TransactionModel add(TransactionModel transaction) throws TransactionException;
	TransactionModel save(TransactionModel transaction) throws TransactionException;
	
	void deleteById(Long transactionId) throws TransactionException;
	
	TransactionModel findById(Long transactionId) throws TransactionException;
	
	List<TransactionModel> findAll();
	
	TransactionModel transaction(String cardNumber,Double amount,String discription) throws CreditCardException;
	
}
