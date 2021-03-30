package com.cg.creditcardpayment.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcardpayment.dao.ICustomerRepository;
import com.cg.creditcardpayment.entity.CustomerEntity;
import com.cg.creditcardpayment.exception.AccountException;
import com.cg.creditcardpayment.exception.CustomerException;
import com.cg.creditcardpayment.model.AccountModel;
import com.cg.creditcardpayment.model.CustomerModel;


@Service
public class CustomerServiceImpl implements ICustomerService {
	
	@Autowired
	private ICustomerRepository customerRepo;
	
	@Autowired
	private EMParse parser;
	
	public CustomerServiceImpl(ICustomerRepository customerRepo) {
		super();
		this.customerRepo = customerRepo;
		this.parser = new EMParse();
	}

	
	public ICustomerRepository getCustomerRepo() {
		return customerRepo;
	}


	public void setCustomerRepo(ICustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}


	public EMParse getParser() {
		return parser;
	}


	public void setParser(EMParse parser) {
		this.parser = parser;
	}

	String constant=" is Already Exists";
	String constant1="Customer ";

	
	@Override
	@Transactional
	public CustomerModel addCustomer(CustomerModel customer,String userId) throws CustomerException {
		if(customer !=null) {
			if(customerRepo.existsById(userId)) {
				throw new CustomerException(constant1+customer.getUserId()+constant);
			}else if (customerRepo.existsByContactNo(customer.getContactNo())) {
				throw new CustomerException("Customer with number "+customer.getContactNo()+constant);
			}else if (customerRepo.existsByEmail(customer.getEmail())) {
				throw new CustomerException("Customer with email "+customer.getEmail()+constant);
			}else {
				customer.setUserId(userId);
				customer=parser.parse(customerRepo.save(parser.parse(customer)));
			}
		}
		return customer;
	}

	@Override
	@Transactional
	public CustomerModel updateCustomer(CustomerModel customer) throws CustomerException {
		if(customer ==null) {
			throw new CustomerException("Customer details cannot be null");
		}
		return parser.parse(customerRepo.save(parser.parse(customer)));
	}

	@Override
	@Transactional
	public void deleteById(String userId) throws CustomerException {
		if(userId==null) {
			throw new CustomerException("CustomerId can not be null");
		}else if(!customerRepo.existsById(userId)) {
			throw new CustomerException(constant1+userId+" is not Exists");
		}
		customerRepo.deleteById(userId);
	}

	@Override
	public CustomerModel findById(String userId) throws CustomerException {
		if(userId==null) {
			throw new CustomerException("CustomerId can not be null");
		}else if(!customerRepo.existsById(userId)) {
			throw new CustomerException(constant1+userId+" is not Exists");
		}
		return parser.parse(customerRepo.findById(userId).orElse(null));
	}

	@Override
	public List<CustomerModel> findAll() {
		return customerRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}

	@Override
	public boolean existsByContactNo(String contactNo) throws CustomerException {
		if(contactNo==null) {
			throw new CustomerException("contact No can not be null");
		}
		return customerRepo.existsByContactNo(contactNo);
	}

	@Override
	public boolean existsByEmail(String email) throws CustomerException {
		if(email==null) {
			throw new CustomerException("email can not be null");
		}
		return customerRepo.existsByEmail(email);
	}

	@Override
	public boolean existsById(String userId) throws CustomerException {
		if(userId==null) {
			throw new CustomerException("Id can not be null");
		}
		return customerRepo.existsById(userId);
	}


	@Override
	@Transactional
	public boolean addAccount(AccountModel account,String customerId) throws AccountException, CustomerException{
		CustomerEntity customer=customerRepo.findById(customerId).orElse(null);
		boolean isAdded=false;
		if(account==null) {
			throw new AccountException("Account can not be null");
		}
		if(customerId==null) {
			throw new CustomerException("customer Id is null");
		}else if(customer == null){
			throw new CustomerException("Customer Id in null");
		}else {
			customer.getAccounts().add(parser.parse(account));
			customer.setAccounts(customer.getAccounts());
			customerRepo.save(customer);
			isAdded=true;
		}
		return isAdded;
	}


	@Override
	public List<AccountModel> getAccounts(String customerId) throws AccountException {
		CustomerEntity customer=customerRepo.findById(customerId).orElse(null);
		if(customer ==null) {
			throw new AccountException("No customer Exists");
		}
		return customer.getAccounts().stream().map(parser::parse).collect(Collectors.toList());
	}
}
