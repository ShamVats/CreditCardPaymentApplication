package com.cg.creditcardpayment.service;

import java.util.List;

import com.cg.creditcardpayment.exception.AccountException;
import com.cg.creditcardpayment.exception.CreditCardException;
import com.cg.creditcardpayment.exception.PaymentException;
import com.cg.creditcardpayment.exception.StatementException;
import com.cg.creditcardpayment.model.PaymentModel;
import com.cg.creditcardpayment.model.StatementModel;

public interface IPaymentService {
	
	boolean existsById(Long paymentId);

	PaymentModel add(PaymentModel payment) throws PaymentException;
	PaymentModel save(PaymentModel payment) throws PaymentException;

	void deleteById(Long paymentId) throws PaymentException;
	
	PaymentModel findById(Long paymentId) throws PaymentException;
	List<PaymentModel> findAll();
	
	PaymentModel payBill(PaymentModel payment,Long statementId,String accountNumber)throws PaymentException, CreditCardException, StatementException, AccountException;
	PaymentModel payBill(PaymentModel payment,Long statementId) throws PaymentException, CreditCardException, StatementException;
	List<StatementModel> pendingBills(String cardNumber) throws CreditCardException;
	List<PaymentModel> paymentHistory (String cardNumber) throws CreditCardException;
	
}
