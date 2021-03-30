package com.cg.creditcardpayment.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.creditcardpayment.exception.CreditCardException;
import com.cg.creditcardpayment.exception.StatementException;
import com.cg.creditcardpayment.model.StatementModel;
import com.cg.creditcardpayment.service.IStatementService;

@RestController
@RequestMapping("/statements")
public class StatementRestController {

	@Autowired
	private IStatementService statementService;
	
	@GetMapping("/getAllStatements")
	public ResponseEntity<List<StatementModel>> findAll() {
		return ResponseEntity.ok(statementService.findAll());
	}
	
	@GetMapping("/getStatement/{statementId}")
	public ResponseEntity<StatementModel> findById(@PathVariable("statementId") Long statementId) throws StatementException{
		return ResponseEntity.ok(statementService.findById(statementId));
	}
	
	@PostMapping("/addStatement")
	public ResponseEntity<StatementModel> add(@RequestBody StatementModel statement) throws StatementException {
		statement=statementService.add(statement);
		return ResponseEntity.ok(statement);
	}
	
	
	@PutMapping("/updateStatement")
	public ResponseEntity<StatementModel> updateUser(@RequestBody StatementModel statement) throws StatementException{
		statement =statementService.save(statement);
		return ResponseEntity.ok(statement);
	}
	
	@GetMapping("/generateBill/{cardNumber}")
	public ResponseEntity<StatementModel> getBill(@PathVariable("cardNumber") String cardNumber) throws CreditCardException, StatementException{
		return ResponseEntity.ok(statementService.getBilledStatement(cardNumber));
	}
	
	@GetMapping("/generateUnBilled/{cardNumber}")
	public ResponseEntity<StatementModel> getUnBill(@PathVariable("cardNumber") String cardNumber) throws CreditCardException{
		return ResponseEntity.ok(statementService.getUnBilledStatement(cardNumber));
	}
	
	@GetMapping("/statementHistory/{cardNumber}")
	public ResponseEntity<List<StatementModel>> statementHistory(@PathVariable("cardNumber") String cardNumber) throws CreditCardException {
		return ResponseEntity.ok(statementService.statementHistory(cardNumber));
	}
	
}
