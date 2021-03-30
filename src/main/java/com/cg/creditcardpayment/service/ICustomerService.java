package com.cg.creditcardpayment.service;

import java.util.List;

import com.cg.creditcardpayment.exception.AccountException;
import com.cg.creditcardpayment.exception.CustomerException;
import com.cg.creditcardpayment.model.AccountModel;
import com.cg.creditcardpayment.model.CustomerModel;

public interface ICustomerService {

	boolean existsByContactNo(String contactNo) throws CustomerException;
	boolean existsByEmail(String email) throws CustomerException;
	boolean existsById(String userId) throws CustomerException;

	CustomerModel addCustomer(CustomerModel customer,String userId) throws CustomerException;
	CustomerModel updateCustomer(CustomerModel customer) throws CustomerException;
	
	void deleteById(String customerId) throws CustomerException;
	
	CustomerModel findById(String customerId) throws CustomerException;
	
	List<CustomerModel> findAll();


	boolean addAccount(AccountModel account,String customerId) throws AccountException, CustomerException;
	List<AccountModel> getAccounts(String customerId) throws AccountException;
	
	

}
