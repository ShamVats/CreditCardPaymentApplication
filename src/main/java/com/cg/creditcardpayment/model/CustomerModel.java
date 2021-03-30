package com.cg.creditcardpayment.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

public class CustomerModel {
	
	private String userId;
	
	@NotNull(message="customer name cannot be null")	
	@NotBlank(message="customer name cannot be blank")
	private String userName;
	
	@NotNull(message="email cannot be null")	
	@NotBlank(message="email cannot be blank")
	@Pattern(regexp="^[A-Za-z0-9]{3,}[@][a-z]{2,}[a-z.]{2,}[a-z]$")
	private String email;
	
	@NotNull(message="number cannot be null")	
	@NotBlank(message="number cannot be blank")
	@Pattern(regexp = "[6-9][0-9]{9}")
	private String contactNo;
	
	@NotNull(message="date of birth cannot be null")	
	@NotBlank(message="date of birth cannot be blank")
	@PastOrPresent(message="Expiry date cannot be in future")
	private  LocalDate dob;
	
	@NotNull(message="address cannot be null")	
	@NotBlank(message="adress cannot be blank")
	private AddressModel address;

	public CustomerModel() {
		/* Default Constructor*/
	}

	/**
	 * @param userId
	 * @param userName
	 * @param email
	 * @param contactNo
	 * @param dob
	 * @param address
	 */
	public CustomerModel(String userId,
			@NotNull(message = "customer name cannot be null") @NotBlank(message = "customer name cannot be blank") String userName,
			@NotNull(message = "email cannot be null") @NotBlank(message = "email cannot be blank") @Pattern(regexp = "^[A-Za-z0-9]{3,}[@][a-z]{2,}[a-z.]{2,}[a-z]$") String email,
			@NotNull(message = "number cannot be null") @NotBlank(message = "number cannot be blank") @Pattern(regexp = "[6-9][0-9]{9}") String contactNo,
			@NotNull(message = "date of birth cannot be null") @NotBlank(message = "date of birth cannot be blank") @PastOrPresent(message = "Expiry date cannot be in future") LocalDate dob,
			@NotNull(message = "address cannot be null") @NotBlank(message = "adress cannot be blank") AddressModel address) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.contactNo = contactNo;
		this.dob = dob;
		this.address = address;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
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
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		CustomerModel other = (CustomerModel) obj;
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
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("userId=%s, userName=%s, email=%s, contactNo=%s, dob=%s, address=%s",
				userId, userName, email, contactNo, dob, address);
	}
	
	
}
