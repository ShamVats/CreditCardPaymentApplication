package com.cg.creditcardpayment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcardpayment.dao.ICreditCardRepository;
import com.cg.creditcardpayment.dao.ICustomerRepository;
import com.cg.creditcardpayment.entity.AccountEntity;
import com.cg.creditcardpayment.entity.CreditCardEntity;
import com.cg.creditcardpayment.entity.CustomerEntity;
import com.cg.creditcardpayment.entity.PaymentEntity;
import com.cg.creditcardpayment.entity.StatementEntity;
import com.cg.creditcardpayment.entity.TransactionEntity;
import com.cg.creditcardpayment.entity.UserEntity;
import com.cg.creditcardpayment.model.AccountModel;
import com.cg.creditcardpayment.model.CreditCardModel;
import com.cg.creditcardpayment.model.CustomerModel;
import com.cg.creditcardpayment.model.PaymentModel;
import com.cg.creditcardpayment.model.StatementModel;
import com.cg.creditcardpayment.model.TransactionModel;
import com.cg.creditcardpayment.model.UserModel;

@Service
public class EMParse {
	@Autowired
	private ICreditCardRepository creditCardRepo;
	
	@Autowired
	private ICustomerRepository customerRepo;
	
	public AccountModel parse(AccountEntity source) {
		return source==null?null:
			new AccountModel(source.getAccountNumber(),
						source.getAccountName(),
						source.getAccountBalance(),
						source.getAccountType());
	}
	
	public AccountEntity parse(AccountModel source) {
		return source==null?null:
				new AccountEntity(source.getAccountNumber(),
							source.getAccountName(),
							source.getAccountBalance(),
							source.getAccountType());
	}

	public CreditCardModel parse(CreditCardEntity source) {
		return source==null?null:
			new CreditCardModel(source.getCardNumber(),
						source.getCardName(),
						source.getCardType(),
						source.getExpiryDate(),
						source.getBankName(),
						source.getCvv(),
						source.getCreditLimit(),
						source.getUsedLimit(),
						source.getCustomer().getUserId());
	}
	
	public CreditCardEntity parse(CreditCardModel source) {
		return source==null?null:
				new CreditCardEntity(source.getCardNumber(),
						source.getCardName(),
						source.getCardType(),
						source.getExpiryDate(),
						source.getBankName(),
						source.getCvv(),
						source.getCardLimit(),
						source.getUsedLimit(),
						customerRepo.findById(source.getCustomerId()).orElse(null));
	}
	
	public CustomerModel parse(CustomerEntity source) {
		return source==null?null:
			new CustomerModel(source.getUserId(),
						source.getName(),
						source.getEmail(),
						source.getContactNo(),
						source.getDob(),
						source.getAddress());
	}
	
	public CustomerEntity parse(CustomerModel source) {
		return source==null?null:
				new CustomerEntity(source.getUserId(),
						source.getUserName(),
						source.getEmail(),
						source.getContactNo(),
						source.getDob(),
						source.getAddress());
	}
	
	public PaymentModel parse(PaymentEntity source) {
		return source==null?null:
			new PaymentModel(source.getPaymentId(),
						source.getMethod(),
						source.getAmount(),
						source.getPaidDate(),
						source.getPaidTime(),
						source.getCard().getCardNumber());
	}
	
	public PaymentEntity parse(PaymentModel source) {
		return source==null?null:
				new PaymentEntity(source.getPaymentId(),
								source.getMethod(),
								source.getPaidDate(),
								source.getPaidTime(),
								source.getAmount(),
								creditCardRepo.findById(source.getCardNumber()).orElse(null));
	}
	
	public StatementModel parse(StatementEntity source) {
		return source==null?null:
			new StatementModel(source.getStatementId(),
						source.getBillAmount(),
						source.getDueAmount(),
						source.getBillDate(),
						source.getDueDate(),
						source.getCreditCard().getCardNumber(),
						source.getCreditCard().getCustomer().getName());
	}
	
	public StatementEntity parse(StatementModel source) {
		return source==null?null:
				new StatementEntity(source.getStatementId(),
							source.getBillAmount(),
							source.getDueAmount(),
							source.getBillDate(),
							source.getDueDate(),
							creditCardRepo.findById(source.getCardNumber()).orElse(null));
	}
	
	public TransactionModel parse(TransactionEntity source) {
		return source==null?null:
			new TransactionModel(source.getTransactionId(),
						source.getCreditCard().getCardNumber(),
						source.getAmount(),
						source.getTransactionDate(),
						source.getTransactionTime(),
						source.getStatus(),
						source.getDescription());
	}
	
	public TransactionEntity parse(TransactionModel source) {
		return source==null?null:
				new TransactionEntity(source.getTransactionId(), 
						source.getStatus(),
						creditCardRepo.findById(source.getCardNumber()).orElse(null),
						source.getAmount(),
						source.getDescription());
	}
	
	public UserModel parse(UserEntity source) {
		return source==null?null:
			new UserModel(source.getUserId(),
						source.getPassword(),
						source.getRole());
	}
	
	public UserEntity parse(UserModel source) {
		return source==null?null:
				new UserEntity(source.getUserId(),
							source.getPassword(),
							source.getRole());
	}
}
