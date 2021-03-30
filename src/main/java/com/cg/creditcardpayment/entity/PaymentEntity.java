package com.cg.creditcardpayment.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cg.creditcardpayment.model.PaymentMethod;

@Entity
@Table(name="payments")
public class PaymentEntity {
	
	@Id
	@Column(name="payment_id")
	private Long paymentId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="payment_method",nullable=false)
	private PaymentMethod method;

	@Column(name="paid_date")
	private LocalDate paidDate;

	@Column(name="paid_time")
	private LocalTime paidTime;
	
	@Column(name="amount",nullable=false)
	private Double amount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="card_number")
	private CreditCardEntity card;

	public PaymentEntity() {
		/*Default Constructor*/
	}

	/**
	 * @param paymentId
	 * @param method
	 * @param amount
	 * @param card
	 */
	public PaymentEntity(Long paymentId, PaymentMethod method,LocalDate paidDate,LocalTime paidTime, Double amount,CreditCardEntity card) {
		super();
		this.paymentId=paymentId;
		this.method = method;
		this.paidDate=paidDate;
		this.paidTime=paidTime;
		this.amount = amount;
		this.card = card;
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

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public CreditCardEntity getCard() {
		return card;
	}

	public void setCard(CreditCardEntity card) {
		this.card = card;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((card == null) ? 0 : card.hashCode());
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
		PaymentEntity other = (PaymentEntity) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (card == null) {
			if (other.card != null)
				return false;
		} else if (!card.equals(other.card))
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
		return String.format("PaymentEntity [paymentId=%s, method=%s, amount=%s, card=%s]", paymentId, method, amount,
				card.getCardNumber());
	}
	
	
	
	
}
