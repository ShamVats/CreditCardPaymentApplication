package com.cg.creditcardpayment.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcardpayment.dao.ICreditCardRepository;
import com.cg.creditcardpayment.dao.IStatementRepository;
import com.cg.creditcardpayment.entity.CreditCardEntity;
import com.cg.creditcardpayment.entity.TransactionEntity;
import com.cg.creditcardpayment.exception.CreditCardException;
import com.cg.creditcardpayment.exception.StatementException;
import com.cg.creditcardpayment.model.StatementModel;


@Service
public class StatementServiceImpl implements IStatementService {
	
	@Autowired
	private IStatementRepository statementRepo;

	@Autowired
	private ICreditCardRepository creditCardRepo;
	
	@Autowired
	private EMParse parser;
	
	public StatementServiceImpl() {
		
	}
	
	public StatementServiceImpl(IStatementRepository statementRepo) {
		super();
		this.statementRepo = statementRepo;
		this.parser = new EMParse();
	}

	@Override
	public StatementModel add(StatementModel statement) throws StatementException {
		if(statement !=null) {
			if(statementRepo.existsById(statement.getStatementId())) {
				throw new StatementException("Statement "+statement.getStatementId()+" is already Exists");
			}else {
				statement=parser.parse(statementRepo.save(parser.parse(statement)));
			}
		}
		return statement;
	}

	@Override
	public StatementModel save(StatementModel statement) throws StatementException {
		if(statement==null) {
			throw new StatementException("Statement cannot be null");
		}
		return parser.parse(statementRepo.save(parser.parse(statement)));
	}

	@Override
	public List<StatementModel> findAll() {
		return statementRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}

	@Override
	public void deleteById(Long statementId) throws StatementException {
		if(statementId==null) {
			throw new StatementException("Statement Id cannot be null");
		}else if(!statementRepo.existsById(statementId)) {
			throw new StatementException("Statement id "+statementId+" Does not exists");
		}
		statementRepo.deleteById(statementId);
		
	}

	@Override
	public StatementModel findById(Long statementId) throws StatementException {
		if(statementId==null) {
			throw new StatementException("Statement Id cannot be null");
		}else if(!statementRepo.existsById(statementId)) {
			throw new StatementException("Statement id "+statementId+" Does not exists");
		}
		return parser.parse(statementRepo.findById(statementId).orElse(null));
	}

	@Override
	public boolean existsById(Long statementId) throws StatementException {
		if(statementId==null) {
			throw new StatementException("Statement Id cannot be Null");
		}
		return statementRepo.existsById(statementId);
	}

	@Override
	public StatementModel getBilledStatement(String cardNumber) throws CreditCardException, StatementException {
		if(cardNumber==null) {
			throw new CreditCardException("Card number cannot be null");
		}
		StatementModel bill=new StatementModel();
		CreditCardEntity card=creditCardRepo.findById(cardNumber).orElse(null);
		if(card==null) {
			throw new CreditCardException("Credit card "+cardNumber+" Does not Exists");
		}
		if(card.getExpiryDate().isBefore(LocalDate.now())) {
			throw new CreditCardException("Credit Card is Expired, Please request new Credit Card");
		}
		bill.setStatementId(0L);
		bill.setBillDate(LocalDate.now());
		
		/*
		 * bill.setBillDate(LocalDate.of(LocalDate.now().getYear(),
		 * LocalDate.now().getMonthValue(), 18));
		 */
		bill.setCardNumber(cardNumber);
		bill.setDueDate(bill.getBillDate().plusDays(20));
				
		Set<TransactionEntity> transaction =card.getTransaction();			
		
		Double amount=transaction.stream().filter(trans->trans.getTransactionDate().plusDays(30).isAfter(LocalDate.now())).mapToDouble( amo -> amo.getAmount()).sum();
		bill.setDueAmount(amount);
		bill.setBillAmount(amount);
		LocalDate lastBillDate=statementRepo.findAll().get(statementRepo.findAll().size()-1).getBillDate();
		StatementModel billStatement=new StatementModel();
		if(lastBillDate.getDayOfMonth()!=LocalDate.now().getDayOfMonth()) {
			billStatement=parser.parse(statementRepo.findAll().get(statementRepo.findAll().size()-1));
		}else {
			billStatement = parser.parse(statementRepo.save(parser.parse(bill)));
		}
		return billStatement;
	}

	@Override
	public StatementModel getUnBilledStatement(String cardNumber) throws CreditCardException {
		if(cardNumber==null) {
			throw new CreditCardException("Card Number cannot be Null");
		}
		StatementModel unBill=new StatementModel();
		unBill.setStatementId(0L);
		unBill.setCardNumber(cardNumber);
		LocalDate lastBillDate=statementRepo.findAll().get(statementRepo.findAll().size()-1).getDueDate();
		unBill.setBillDate(lastBillDate.plusDays(30));
		unBill.setDueDate(unBill.getBillDate().plusDays(20));
		
		CreditCardEntity credit=creditCardRepo.findById(cardNumber).orElse(null);
		
		if(credit==null) {
			throw new CreditCardException("Credit card"+cardNumber+" does not Exists");
		}
		Set<TransactionEntity> transaction =credit.getTransaction();	
		
		Double amount=transaction.stream().filter(trans->trans.getTransactionDate().isAfter(lastBillDate.plusDays(1))).mapToDouble(amo -> amo.getAmount()).sum();
		
		unBill.setDueAmount(amount);
		unBill.setBillAmount(amount);
		unBill=parser.parse(statementRepo.save(parser.parse(unBill)));
		statementRepo.deleteById(unBill.getStatementId());
		return unBill;
	}

	@Override
	public List<StatementModel> statementHistory(String cardNumber) throws CreditCardException {
		if(cardNumber==null) {
			throw new CreditCardException("Card Number cannot be Null");
		}
		CreditCardEntity card=creditCardRepo.findById(cardNumber).orElse(null);
		if(card==null) {
			throw new CreditCardException("Credit Card "+cardNumber+" doesnot Exists");
		}
		return card.getStatement().stream().map(parser::parse).collect(Collectors.toList());
	}

	@Override
	public StatementModel findByBillDate(LocalDate billDate) {
		// TODO Auto-generated method stub
		return parser.parse(statementRepo.findByBillDate(billDate));
	}

}
