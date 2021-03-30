package com.cg.creditcardpayment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
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

import com.cg.creditcardpayment.dao.ICustomerRepository;
import com.cg.creditcardpayment.entity.CustomerEntity;
import com.cg.creditcardpayment.exception.CustomerException;
import com.cg.creditcardpayment.model.AddressModel;
import com.cg.creditcardpayment.model.CustomerModel;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

	@Mock
	private ICustomerRepository customerRepo;
	
	@InjectMocks
	private CustomerServiceImpl service;

	@Test
	@DisplayName("CustomerDetails should retrive")
	void testGetAll() {
		AddressModel address1=new AddressModel("10-10A","kbr","Jublihills","Hydrabad","Telangana",500055);
		AddressModel address2=new AddressModel("10-10B","kbr","Jublihills","Hydrabad","Telangana",500055);
		List<CustomerEntity> testData=Arrays.asList(new CustomerEntity[] {
				new CustomerEntity("U107","Venkata","venkatasai1479@gmail.com","7207388240",LocalDate.parse("1999-10-18"),address1),
				new CustomerEntity("U106","Venkata sai","venkatasai1478@gmail.com","7207388239",LocalDate.parse("1999-10-18"),address2)
		});
		
		Mockito.when(customerRepo.findAll()).thenReturn(testData);
		
		List<CustomerModel> expected=Arrays.asList(new CustomerModel[] {
				new CustomerModel("U107","Venkata","venkatasai1479@gmail.com","7207388240",LocalDate.parse("1999-10-18"),address1),
				new CustomerModel("U106","Venkata sai","venkatasai1478@gmail.com","7207388239",LocalDate.parse("1999-10-18"),address2)
		});
		
		List<CustomerModel> actual = service.findAll();
		
		assertEquals(expected,actual);

	}
	
	@Test
	@DisplayName("CustomerDetails add")
	void testAdd() throws CustomerException {
		AddressModel address1=new AddressModel("10-10A","kbr","Jublihills","Hydrabad","Telangana",500055);
		
		CustomerEntity customer1=new CustomerEntity("U107","Venkata","venkatasai1479@gmail.com","7207388240",LocalDate.parse("1999-10-18"),address1);

		Mockito.when(customerRepo.save(customer1)).thenReturn(customer1);

		CustomerModel expected=new CustomerModel("U107","Venkata","venkatasai1479@gmail.com","7207388240",LocalDate.parse("1999-10-18"),address1);
				
		CustomerModel actual = service.addCustomer(service.getParser().parse(customer1),customer1.getUserId());
		
		assertEquals(expected,actual);

	}
	
	@Test
	@DisplayName("CustomerDetails should delete")
	void testDelete() throws CustomerException {
		AddressModel address1=new AddressModel("10-10A","kbr","Jublihills","Hydrabad","Telangana",500055);
		
		CustomerEntity customer1=new CustomerEntity("U107","Venkata","venkatasai1479@gmail.com","7207388240",LocalDate.parse("1999-10-18"),address1);

		Mockito.when(customerRepo.save(customer1)).thenReturn(customer1);

		CustomerModel expected=new CustomerModel("U107","Venkata","venkatasai1479@gmail.com","7207388240",LocalDate.parse("1999-10-18"),address1);
		
		CustomerModel added = service.addCustomer(service.getParser().parse(customer1),customer1.getUserId());
		
		assertEquals(expected,added);
		
		Mockito.doNothing().when(customerRepo).deleteById(added.getUserId());
		Mockito.when(customerRepo.existsById(added.getUserId())).thenReturn(true);
		
		service.deleteById(added.getUserId());
		
		Mockito.when(customerRepo.existsById(added.getUserId())).thenReturn(false);
		boolean test=service.existsById(added.getUserId());
		
		assertFalse(test);
		
	}
	
	@Test
	@DisplayName("get by Id ")
	void testGetById () throws CustomerException {
		AddressModel address1=new AddressModel("10-10A","kbr","Jublihills","Hydrabad","Telangana",500055);
		CustomerEntity testdata=new CustomerEntity("U107","Venkata","venkatasai1479@gmail.com","7207388240",LocalDate.parse("1999-10-18"),address1);
		
		CustomerModel expected=new CustomerModel("U107","Venkata","venkatasai1479@gmail.com","7207388240",LocalDate.parse("1999-10-18"),address1);
		
		
		Mockito.when(customerRepo.findById(testdata.getUserId())).thenReturn(Optional.of(testdata));
		Mockito.when(customerRepo.existsById(testdata.getUserId())).thenReturn(true);
		
		CustomerModel actual=service.findById(testdata.getUserId());
		
		assertEquals(expected,actual);
	}
	
	@Test
	@DisplayName("get by Id not exists")
	void testGetByIdNotExists () throws CustomerException {
		AddressModel address1=new AddressModel("10-10A","kbr","Jublihills","Hydrabad","Telangana",500055);
		CustomerEntity testdata=new CustomerEntity("U107","Venkata","venkatasai1479@gmail.com","7207388240",LocalDate.parse("1999-10-18"),address1);
		
		Mockito.when(customerRepo.findById(testdata.getUserId())).thenReturn(Optional.empty());
		Mockito.when(customerRepo.existsById(testdata.getUserId())).thenReturn(true);
		
		CustomerModel actual = service.findById(testdata.getUserId());
		assertNull(actual);
	}
	
	@Test
	@DisplayName("exists by number ")
	void testExistsBynumber () throws CustomerException {
		AddressModel address1=new AddressModel("10-10A","kbr","Jublihills","Hydrabad","Telangana",500055);
		CustomerEntity testdata=new CustomerEntity("U107","Venkata","venkatasai1479@gmail.com","7207388240",LocalDate.parse("1999-10-18"),address1);
		
		boolean expected=true;
		
		Mockito.when(customerRepo.existsByContactNo(testdata.getContactNo())).thenReturn(true);
	
		boolean actual=service.existsByContactNo(testdata.getContactNo());
		
		assertEquals(expected,actual);
	}
	
	@Test
	@DisplayName("exists by Email ")
	void testExistsByEmail() throws CustomerException {
		AddressModel address1=new AddressModel("10-10A","kbr","Jublihills","Hydrabad","Telangana",500055);
		CustomerEntity testdata=new CustomerEntity("U107","Venkata","venkatasai1479@gmail.com","7207388240",LocalDate.parse("1999-10-18"),address1);
		
		boolean expected=true;
		
		Mockito.when(customerRepo.existsByEmail(testdata.getEmail())).thenReturn(true);
	
		boolean actual=service.existsByEmail(testdata.getEmail());
		
		assertEquals(expected,actual);
	}
	
	@Test
	@DisplayName("get by id return null")
	void testGetByIdNull() throws CustomerException {		
		
		Mockito.when(customerRepo.findById("U101")).thenReturn(Optional.empty());
		Mockito.when(customerRepo.existsById("U101")).thenReturn(true);
		
		CustomerModel actual = service.findById("U101");
		assertNull(actual);
	}
	
}
