package com.cg.creditcardpayment.service;

import java.util.List;
import java.util.Set;

import com.cg.creditcardpayment.exception.AccountException;
import com.cg.creditcardpayment.exception.CustomerException;
import com.cg.creditcardpayment.model.AccountModel;

public interface IAccountService {
	
	boolean existsById(String accountNumber) throws AccountException;
	
	AccountModel add(AccountModel account) throws AccountException;
	AccountModel save(AccountModel account) throws AccountException;
	
	void deleteById(String accountNumber) throws AccountException;
	
	void deleteCustomerAccount(String customerId, String accountNumber) throws AccountException, CustomerException;
	
	AccountModel findById(String accountNumber) throws AccountException;
	
	List<AccountModel> findAll();
	
	AccountModel addByCustomer(AccountModel account,String customerId) throws AccountException, CustomerException;
	Set<AccountModel> findAllByCustomerId(String customerId) throws CustomerException;
}
