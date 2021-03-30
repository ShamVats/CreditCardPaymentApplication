package com.cg.creditcardpayment.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaymentModel {
	
	private Long paymentId;
	
	@NotNull(message="payment method cannot be null")	
	@NotBlank(message="payment method cannot be blank")
	private PaymentMethod method;
	
	@NotNull(message="amount cannot be null")	
	@NotBlank(message="amount cannot be blank")
	private Double amount;
	
	@NotNull(message="credit card cannot be null")	
	@NotBlank(message="credit card cannot be blank")
	private String cardNumber;
	
	@NotNull(message="paid date cannot be null")	
	@NotBlank(message="paid date cannot be blank")
	private LocalDate paidDate;
	
	@NotNull(message="paid time cannot be null")	
	@NotBlank(message="paid time cannot be blank")
	private LocalTime paidTime;
	
	
	

	public PaymentModel() {
		/*Default Constructor*/
	}

	

	/**
	 * @param paymentId
	 * @param method
	 * @param amount
	 * @param cardNumber
	 */
	public PaymentModel(Long paymentId,
			@NotNull(message = "payment method cannot be null") @NotBlank(message = "payment method cannot be blank") PaymentMethod method,
			@NotNull(message = "amount cannot be null") @NotBlank(message = "amount cannot be blank") Double amount,
			@NotNull(message="paid date cannot be null") @NotBlank(message="paid date cannot be blank") LocalDate paidDate,
			@NotNull(message="paid time cannot be null") @NotBlank(message="paid time cannot be blank") LocalTime paidTime,
			@NotNull(message = "credit card cannot be null") @NotBlank(message = "credit card cannot be blank") String cardNumber) {
		super();
		this.paymentId = paymentId;
		this.method = method;
		this.paidDate=paidDate;
		this.paidTime=paidTime;
		this.amount = amount;
		this.cardNumber = cardNumber;
	}



	public LocalDate getPaidDate() {
		return paidDate;
	}



	public void setPaidDate(LocalDate paidDate) {
		this.paidDate = paidDate;
	}



	public LocalTime getPaidTime() {
		return paidTime;
	}



	public void setPaidTime(LocalTime paidTime) {
		this.paidTime = paidTime;
	}



	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public PaymentMethod getMethod() {
		return method;
	}

	public void setMethod(PaymentMethod method) {
		this.method = method;
	}



	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((paymentId == null) ? 0 : paymentId.hashCode());
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
		PaymentModel other = (PaymentModel) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (paymentId == null) {
			if (other.paymentId != null)
				return false;
		} else if (!paymentId.equals(other.paymentId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("PaymentModel [paymentId=%s, method=%s, amount=%s, cardNumber=%s]", paymentId, method,
				amount, cardNumber);
	}
		
	

}
