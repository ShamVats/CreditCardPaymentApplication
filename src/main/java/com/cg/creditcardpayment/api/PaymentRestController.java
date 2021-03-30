package com.cg.creditcardpayment.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.creditcardpayment.exception.AccountException;
import com.cg.creditcardpayment.exception.CreditCardException;
import com.cg.creditcardpayment.exception.PaymentException;
import com.cg.creditcardpayment.exception.StatementException;
import com.cg.creditcardpayment.model.PaymentModel;
import com.cg.creditcardpayment.model.StatementModel;
import com.cg.creditcardpayment.service.IPaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentRestController {

	@Autowired
	private IPaymentService paymentService;
	
	@GetMapping("/getAllPayments")
	public ResponseEntity<List<PaymentModel>> findAll() {
		return ResponseEntity.ok(paymentService.findAll());
	}
	
	@GetMapping("/getPayment/{paymentId}")
	public ResponseEntity<PaymentModel> findById(@PathVariable("paymentId") Long paymentId) throws PaymentException{
		return ResponseEntity.ok(paymentService.findById(paymentId));
	}
	
	@PostMapping("/addPayment")
	public ResponseEntity<PaymentModel> add(@RequestBody PaymentModel payment) throws PaymentException {
		payment=paymentService.add(payment);
		return ResponseEntity.ok(payment);
	}
	
	@GetMapping("/pendingBills/{cardNumber}")
	public ResponseEntity<List<StatementModel>> getPendingStatements(@PathVariable("cardNumber") String cardNumber) throws CreditCardException{
		return ResponseEntity.ok(paymentService.pendingBills(cardNumber));
	}
	
	@PostMapping("/pendingBills/payUsingUPI/{statementId}")
	public ResponseEntity<PaymentModel> paybill(@RequestBody PaymentModel pay,@PathVariable("statementId") Long statementId) throws PaymentException, CreditCardException, StatementException{
		return ResponseEntity.ok(paymentService.payBill(pay,statementId));
	}
	
	@PostMapping("/pendingBills/payUsingAccount/{statementId}/{accountNumber}")
	public ResponseEntity<PaymentModel> paybillUsingAccount(@RequestBody PaymentModel pay,@PathVariable("statementId") Long statementId,@PathVariable("accountNumber") String accountNumber) throws PaymentException, CreditCardException, StatementException, AccountException{
		return ResponseEntity.ok(paymentService.payBill(pay,statementId,accountNumber));
	}
	
	@GetMapping("/paymentHistory/{cardNumber}")
	public ResponseEntity<List<PaymentModel>> paymentHistory(@PathVariable("cardNumber") String cardNumber) throws CreditCardException{
		return ResponseEntity.ok(paymentService.paymentHistory(cardNumber));
	}
	
}
