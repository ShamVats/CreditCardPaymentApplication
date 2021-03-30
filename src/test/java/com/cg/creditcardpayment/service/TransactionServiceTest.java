package com.cg.creditcardpayment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.creditcardpayment.dao.ITransactionRepository;
import com.cg.creditcardpayment.entity.CreditCardEntity;
import com.cg.creditcardpayment.entity.CustomerEntity;
import com.cg.creditcardpayment.entity.TransactionEntity;
import com.cg.creditcardpayment.exception.TransactionException;
import com.cg.creditcardpayment.model.CardName;
import com.cg.creditcardpayment.model.CardType;
import com.cg.creditcardpayment.model.TransactionModel;
import com.cg.creditcardpayment.model.TransactionStatus;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

	@Mock
	private ITransactionRepository transactionRepo;
	
//	@Mock
//	private ICreditCardRepository creditCardRepo;
	
	@InjectMocks
	private TransactionServiceImpl service;
	
//	@InjectMocks
//	private CreditCardServiceImpl creditCard;

	@Test
	@DisplayName("TransactionDetails should retrive")
	void testGetAll() {
		CreditCardEntity creditCard1=new CreditCardEntity("2568479632140",CardName.VISA,CardType.GOLD,LocalDate.parse("2022-10-18"),"SBI",623,10000.0,10000.0,new CustomerEntity());
		
		List<TransactionEntity> testData=Arrays.asList(new TransactionEntity[] {
				new TransactionEntity(1L,TransactionStatus.SUCCESSFUL,creditCard1,6000.0,"buied"),
				new TransactionEntity(2L,TransactionStatus.SUCCESSFUL,creditCard1,3000.0,"sendToFriend")
		});
		
		Mockito.when(transactionRepo.findAll()).thenReturn(testData);
		
		List<TransactionModel> expected=Arrays.asList(new TransactionModel[] {
				new TransactionModel(1L,creditCard1.getCardNumber(),6000.0,LocalDate.now(),LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond()), TransactionStatus.SUCCESSFUL,"buied"),
				new TransactionModel(2L,creditCard1.getCardNumber(),3000.0,LocalDate.now(),LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond()),TransactionStatus.SUCCESSFUL,"sendToFriend")
		});
		
		List<TransactionModel> actual = service.findAll();
		
		assertEquals(expected,actual);

	}
	
	@Test
	@DisplayName("get by Id ")
	void testGetById () throws TransactionException {
		CreditCardEntity creditCard1=new CreditCardEntity("2568479632140",CardName.VISA,CardType.GOLD,LocalDate.parse("2022-10-18"),"SBI",623,10000.0,10000.0,new CustomerEntity());
		
		TransactionEntity testdata=new TransactionEntity(1L,TransactionStatus.SUCCESSFUL,creditCard1,6000.0,"buied");
		
		TransactionModel expected=new TransactionModel(1L,creditCard1.getCardNumber(),6000.0,LocalDate.now(),LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond()),TransactionStatus.SUCCESSFUL,"buied");
		
		
		Mockito.when(transactionRepo.findById(testdata.getTransactionId())).thenReturn(Optional.of(testdata));
		Mockito.when(transactionRepo.existsById(testdata.getTransactionId())).thenReturn(true);
		
		TransactionModel actual=service.findById(testdata.getTransactionId());
		
		assertEquals(expected,actual);
	}
	
	@Test
	@DisplayName("get by id return null")
	void testGetByIdNull() throws TransactionException {		
		
		Mockito.when(transactionRepo.findById(1L)).thenReturn(Optional.empty());
		Mockito.when(transactionRepo.existsById(1L)).thenReturn(true);
		
		TransactionModel actual = service.findById(1L);
		assertNull(actual);
	}
	
}
