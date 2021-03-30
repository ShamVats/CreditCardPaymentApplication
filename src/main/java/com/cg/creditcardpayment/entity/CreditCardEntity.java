package com.cg.creditcardpayment.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cg.creditcardpayment.model.CardName;
import com.cg.creditcardpayment.model.CardType;


@Entity
@Table(name="creditcards")
public class CreditCardEntity {
	
	@Id
	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="card_name", nullable=false)
	@Enumerated(EnumType.STRING)
	private CardName cardName;

	@Column(name="card_type",nullable=false)
	@Enumerated(EnumType.STRING)
	private CardType cardType;
	
	@Column(name="expiry_date", nullable=false)
    private LocalDate expiryDate;
	
	@Column(name="bank_name", nullable=false)
	private String bankName;
	
	@Column(name="cvv", nullable=false)
    private Integer cvv;
	
	@Column(name="credit_limit",nullable=false)
	private Double creditLimit;
	
	@Column(name="used_limit")
	private Double usedLimit;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private CustomerEntity customer;

	@OneToMany(mappedBy="creditCard",cascade=CascadeType.ALL)
	private Set<TransactionEntity> transaction;
	

	@OneToMany(mappedBy="creditCard",cascade=CascadeType.ALL)
	private Set<StatementEntity> statement;

	@OneToMany(mappedBy="card",cascade=CascadeType.ALL)
	private List<PaymentEntity> payments;
	
	public CreditCardEntity() {
		/* Default Constructor */
	}
	
	public Double getCreditLimit() {
		return creditLimit;
	}

	/**
	 * @param cardNumber
	 * @param cardName
	 * @param cardType
	 * @param expiryDate
	 * @param bankName
	 * @param cvv
	 * @param creditLimit
	 * @param usedLimit
	 * @param customer
	 */
	public CreditCardEntity(String cardNumber, CardName cardName, CardType cardType, LocalDate expiryDate,
			String bankName, Integer cvv, Double creditLimit, Double usedLimit, CustomerEntity customer) {
		super();
		this.cardNumber = cardNumber;
		this.cardName = cardName;
		this.cardType = cardType;
		this.expiryDate = expiryDate;
		this.bankName = bankName;
		this.cvv = cvv;
		this.creditLimit = creditLimit;
		this.usedLimit = usedLimit;
		this.customer = customer;
	}

	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public Double getUsedLimit() {
		return usedLimit;
	}

	public void setUsedLimit(Double usedLimit) {
		this.usedLimit = usedLimit;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public Set<TransactionEntity> getTransaction() {
		return transaction;
	}

	public void setTransaction(Set<TransactionEntity> transaction) {
		this.transaction = transaction;
	}

	public Set<StatementEntity> getStatement() {
		return statement;
	}

	public void setStatement(Set<StatementEntity> statement) {
		this.statement = statement;
	}

	public List<PaymentEntity> getPayments() {
		return payments;
	}

	public void setPayments(List<PaymentEntity> payments) {
		this.payments = payments;
	}
	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	public CardName getCardName() {
		return cardName;
	}


	public void setCardName(CardName cardName) {
		this.cardName = cardName;
	}


	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getCvv() {
		return cvv;
	}


	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result + ((cardName == null) ? 0 : cardName.hashCode());
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((cardType == null) ? 0 : cardType.hashCode());
		result = prime * result + ((cvv == null) ? 0 : cvv.hashCode());
		result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
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
		CreditCardEntity other = (CreditCardEntity) obj;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (cardName == null) {
			if (other.cardName != null)
				return false;
		} else if (!cardName.equals(other.cardName))
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (cardType == null) {
			if (other.cardType != null)
				return false;
		} else if (!cardType.equals(other.cardType))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				"cardNumber=%s, cardName=%s, cardType=%s, expiryDate=%s, bankName=%s, cvv=%s, customer=%s",
				cardNumber, cardName, cardType, expiryDate, bankName, cvv, customer.getName());
	}

	
    
}
