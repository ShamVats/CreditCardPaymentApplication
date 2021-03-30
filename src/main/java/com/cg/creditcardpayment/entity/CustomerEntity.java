package com.cg.creditcardpayment.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cg.creditcardpayment.model.AddressModel;


@Entity
@Table(name="customers")
public class CustomerEntity {
	
	@Id
	@Column(name="user_id")
	private String userId;
	
	@Column(name="user_name",nullable=false)
	private String userName;
	
	@Column(name="email",nullable=false)
	private String email;
	
	@Column(name="contact_number",nullable=false)
	private String contactNo;
	
	@Column(name="date_of_birth",nullable=false)
	private  LocalDate dob;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="user")
	private UserEntity user;
		
	@Embedded
	private AddressModel address;
	

	@OneToMany(mappedBy="customer")
	private Set<CreditCardEntity> creditCard;

	
	@ManyToMany(fetch=FetchType.LAZY,cascade= CascadeType.ALL)
	@JoinTable(name="customer_account",
	joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="account_number"))
	private Set<AccountEntity> accounts;
	
	public CustomerEntity() {
		/* Default Constructor*/
	}
	
	public CustomerEntity(String userId, String userName, String email, String contactNo, LocalDate dob, AddressModel address) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.contactNo = contactNo;
		this.dob = dob;
		this.address = address;
	}
	
	
	public Set<AccountEntity> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<AccountEntity> accounts) {
		this.accounts = accounts;
	}

	public Set<CreditCardEntity> getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(Set<CreditCardEntity> creditCard) {
		this.creditCard = creditCard;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return userName;
	}
	public void setName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public AddressModel getAddress() {
		return address;
	}
	public void setAddress(AddressModel address) {
		this.address = address;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((contactNo == null) ? 0 : contactNo.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerEntity other = (CustomerEntity) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (contactNo == null) {
			if (other.contactNo != null)
				return false;
		} else if (!contactNo.equals(other.contactNo))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.format("userId=%s, userName=%s, email=%s, contactNo=%s, dob=%s, address=%s", userId, userName,
				email, contactNo, dob, address);
	}

	
}
