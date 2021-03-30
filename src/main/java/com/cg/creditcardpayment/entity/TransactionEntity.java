package com.cg.creditcardpayment.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cg.creditcardpayment.model.TransactionStatus;

@Entity
@Table(name="transactions")
public class TransactionEntity {

	@Id
	@Column(name="trans_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long transactionId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status",nullable=false)
	private TransactionStatus status;
	
	@Column(name="trans_date",nullable=false)
	private LocalDate transactionDate;
	
	@Column(name="trans_time",nullable=false)
	private LocalTime transactionTime;
	
	@Column(name="amount",nullable=false)
	private Double amount;
	
	@Column(name="description",nullable=false)
	private String description;
	
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name="card_number")
	private CreditCardEntity creditCard;
	
	public TransactionEntity() {
		/* Default Constructor*/
	}


	public TransactionEntity(Long transactionId, TransactionStatus status, CreditCardEntity creditCard,
			Double amount, String description) {
		super();
		this.transactionId=transactionId;
		this.status = status;
		this.transactionDate = LocalDate.now();
		this.transactionTime = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond());
		this.creditCard = creditCard;
		this.amount = amount;
		this.description = description;
	}

	public Long getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}


	public TransactionStatus getStatus() {
		return status;
	}


	public void setStatus(TransactionStatus status) {
		this.status = status;
	}


	public LocalDate getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}


	public LocalTime getTransactionTime() {
		return transactionTime;
	}


	public void setTransactionTime(LocalTime transactionTime) {
		this.transactionTime = transactionTime;
	}



	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public CreditCardEntity getCreditCard() {
		return creditCard;
	}


	public void setCreditCard(CreditCardEntity creditCard) {
		this.creditCard = creditCard;
	}
	
	public String getCardNumber() {
		return creditCard.getCardNumber();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((creditCard == null) ? 0 : creditCard.hashCode());
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((transactionTime == null) ? 0 : transactionTime.hashCode());
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
		TransactionEntity other = (TransactionEntity) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (creditCard == null) {
			if (other.creditCard != null)
				return false;
		} else if (!creditCard.equals(other.creditCard))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (transactionTime == null) {
			if (other.transactionTime != null)
				return false;
		} else if (!transactionTime.equals(other.transactionTime))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return String.format(
				"TransactionEntity [transactionId=%s, status=%s, transactionDate=%s, transactionTime=%s, amount=%s, description=%s, creditCard=%s]",
				transactionId, status, transactionDate, transactionTime, amount, description, creditCard);
	}


		
	
}
