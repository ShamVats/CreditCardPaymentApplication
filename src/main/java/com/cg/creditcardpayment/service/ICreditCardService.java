package com.cg.creditcardpayment.service;

import java.util.List;
import java.util.Set;

import com.cg.creditcardpayment.exception.CreditCardException;
import com.cg.creditcardpayment.exception.CustomerException;
import com.cg.creditcardpayment.model.CreditCardModel;

public interface ICreditCardService {


	boolean existsById(String cardNumber) throws CreditCardException;
	
	CreditCardModel add(CreditCardModel creditCard) throws CreditCardException;
	CreditCardModel save(CreditCardModel creditCard) throws CreditCardException;
	
	void deleteById(String cardNumber) throws CreditCardException;
	
	CreditCardModel findById(String cardNumber) throws CreditCardException;
	
	List<CreditCardModel> findAll();
	
	Set<CreditCardModel> findByCustomerId(String customerId) throws CreditCardException, CustomerException;

	CreditCardModel addToCustomer(CreditCardModel creditCard, String customerId)throws CreditCardException, CustomerException;

	void deleteCreditCardOfCustomer(String customerId, String cardNumber) throws CreditCardException, CustomerException;
}
