package com.cg.creditcardpayment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import com.cg.creditcardpayment.dao.IAccountRepository;
import com.cg.creditcardpayment.entity.AccountEntity;
import com.cg.creditcardpayment.exception.AccountException;
import com.cg.creditcardpayment.model.AccountModel;
import com.cg.creditcardpayment.model.AccountType;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

	@Mock
	private IAccountRepository accountRepo;
	
	@InjectMocks
	private AccountServiceImpl service;

	@Test
	@DisplayName("AccountDetails should retrive")
	void testGetAll() {
		List<AccountEntity> testData=Arrays.asList(new AccountEntity[] {
				new AccountEntity("42356879562","Venkata sai",50000.0,AccountType.SAVINGS),
				new AccountEntity("42356879563","Venkata",40000.0,AccountType.SAVINGS),
				new AccountEntity("42356879564","Sai",90000.0,AccountType.CURRENT)
		});
		
		Mockito.when(accountRepo.findAll()).thenReturn(testData);
		
		List<AccountModel> expected=Arrays.asList(new AccountModel[] {
				new AccountModel("42356879562","Venkata sai",50000.0,AccountType.SAVINGS),
				new AccountModel("42356879563","Venkata",40000.0,AccountType.SAVINGS),
				new AccountModel("42356879564","Sai",90000.0,AccountType.CURRENT)
		});
		
		List<AccountModel> actual = service.findAll();
		
		assertEquals(expected,actual);

	}
	
	@Test
	@DisplayName("AccountDetails add")
	void testAdd() throws AccountException {
		AccountEntity account1=new AccountEntity("42356879562","Venkata sai",50000.0,AccountType.SAVINGS);
		
		Mockito.when(accountRepo.save(account1)).thenReturn(account1);

		AccountModel expected=new AccountModel("42356879562","Venkata sai",50000.0,AccountType.SAVINGS);
		
		AccountModel actual = service.add(service.getParser().parse(account1));
		
		assertEquals(expected,actual);

	}
	
	@Test
	@DisplayName("AccountDetails should delete")
	void testDelete() throws AccountException {
		AccountEntity account1=new AccountEntity("42356879562","Venkata sai",50000.0,AccountType.SAVINGS);
		
		Mockito.when(accountRepo.save(account1)).thenReturn(account1);
		

		AccountModel added = service.add(service.getParser().parse(account1));
		
		Mockito.doNothing().when(accountRepo).deleteById(added.getAccountNumber());
		Mockito.when(accountRepo.existsById(account1.getAccountNumber())).thenReturn(true);
		service.deleteById(added.getAccountNumber());
		boolean test=service.existsById(added.getAccountNumber());
		
		assertTrue(test);
		
	}
	
	@Test
	@DisplayName("get by Id ")
	void testGetById () throws AccountException {
		AccountEntity testdata=new AccountEntity("42356879564","Sai",90000.0,AccountType.SAVINGS);
		
		AccountModel expected=new AccountModel("42356879564","Sai",90000.0,AccountType.SAVINGS);
		
		
		Mockito.when(accountRepo.findById(testdata.getAccountNumber())).thenReturn(Optional.of(testdata));
	
		AccountModel actual=service.findById(testdata.getAccountNumber());
		
		assertEquals(expected,actual);
	}
	
	@Test
	@DisplayName("get by id return null")
	void testGetByIdNull() throws AccountException {		
		
		Mockito.when(accountRepo.findById("425631257892")).thenReturn(Optional.empty());
		
		AccountModel actual = service.findById("425631257892");
		assertNull(actual);
	}
	
}
