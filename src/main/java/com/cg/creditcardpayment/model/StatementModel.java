package com.cg.creditcardpayment.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StatementModel {	
	
	private Long statementId;
	
	@NotNull(message="due amount cannot be null")	
	@NotBlank(message="due amount cannot be blank")
	private Double dueAmount;
	
	@NotNull(message="bill amount cannot be null")	
	@NotBlank(message="bill amount cannot be blank")
	private Double billAmount;
	
	@NotNull(message="bill date cannot be null")	
	@NotBlank(message="bill date cannot be blank")
	private LocalDate billDate;
	
	@NotNull(message="due date cannot be null")	
	@NotBlank(message="due date cannot be blank")
	private LocalDate dueDate;

	@NotNull(message="credit card cannot be null")	
	@NotBlank(message="credit card cannot be blank")
	private String cardNumber;

	@NotNull(message="customer name cannot be null")	
	@NotBlank(message="customer name cannot be blank")
	private String customerName;
	
	public StatementModel() {
		/*Default Constructor*/
	}

	

	/**
	 * @param statementId
	 * @param dueAmount
	 * @param billDate
	 * @param dueDate
	 * @param cardNumber
	 */
	public StatementModel(Long statementId,
			@NotNull(message = "bill amount cannot be null") @NotBlank(message = "bill amount cannot be blank") Double billAmount,
			Double dueAmount,
			@NotNull(message = "bill date cannot be null") @NotBlank(message = "bill date cannot be blank") LocalDate billDate,
			@NotNull(message = "due date cannot be null") @NotBlank(message = "due date cannot be blank") LocalDate dueDate,
			@NotNull(message = "credit card cannot be null") @NotBlank(message = "credit card cannot be blank") String cardNumber,
			@NotNull(message="customer name cannot be null") @NotBlank(message="customer name cannot be blank") String customerName) {
		super();
		this.statementId=statementId;
		this.dueAmount = dueAmount;
		this.billDate = billDate;
		this.billAmount=billAmount;
		this.dueDate = dueDate;
		this.cardNumber = cardNumber;
		this.customerName=customerName;
	}



	public Double getBillAmount() {
		return billAmount;
	}



	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}



	public String getCardNumber() {
		return cardNumber;
	}



	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}



	public Long getStatementId() {
		return statementId;
	}

	public void setStatementId(Long statementId) {
		this.statementId = statementId;
	}

	public Double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public LocalDate getBillDate() {
		return billDate;
	}

	public void setBillDate(LocalDate billDate) {
		this.billDate = billDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	

	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billAmount == null) ? 0 : billAmount.hashCode());
		result = prime * result + ((billDate == null) ? 0 : billDate.hashCode());
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
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
		StatementModel other = (StatementModel) obj;
		if (billAmount == null) {
			if (other.billAmount != null)
				return false;
		} else if (!billAmount.equals(other.billAmount))
			return false;
		if (billDate == null) {
			if (other.billDate != null)
				return false;
		} else if (!billDate.equals(other.billDate))
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return String.format(
				"StatementModel [statementId=%s, dueAmount=%s, billAmount=%s, billDate=%s, dueDate=%s, cardNumber=%s, customerName=%s]",
				statementId, dueAmount, billAmount, billDate, dueDate, cardNumber, customerName);
	}	
	
	



		
	
}
