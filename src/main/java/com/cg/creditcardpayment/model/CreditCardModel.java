package com.cg.creditcardpayment.model;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class CreditCardModel {
	
	@NotNull(message="card number cannot be null")	
	@NotBlank(message="card number cannot be blank")
	@Pattern(regexp = "[0-9]{16}")
	private String cardNumber;
	
	
	@NotNull(message="card name cannot be null")	
	@NotBlank(message="card name cannot be blank")
	private CardName cardName;
	
	@NotNull(message="card type cannot be null")	
	@NotBlank(message="card type cannot be blank")
	private CardType cardType;
	
	@NotNull(message="card expiry date cannot be null")	
	@Future(message="Expiry date cannot be in future")
    private LocalDate expiryDate;
	
	
	@NotNull(message="Bank name cannot be null")	
	@NotBlank(message="Bank name cannot be blank")
	private String bankName;
	
	@NotNull(message="cvv cannot be null")	
	@NotBlank(message="cvv cannot be blank")
	@Pattern(regexp = "[0-9]{3}")
    private Integer cvv;
	
	
	@NotNull(message="limit cannot be null")	
	@NotBlank(message="limit cannot be blank")
    private Double cardLimit;
	
	@NotNull(message="limit cannot be null")	
	@NotBlank(message="limit cannot be blank")
    private Double usedLimit;
	
	@NotNull(message="customerId cannot be null")	
	@NotBlank(message="customerId cannot be blank")
	@JsonProperty(access = Access.WRITE_ONLY)
    private String customerId;
	
	public CreditCardModel() {
		/* Default Constructor */
	}


	/**
	 * @param cardNumber
	 * @param cardName
	 * @param cardType
	 * @param expiryDate
	 * @param bankName
	 * @param cvv
	 * @param cardLimit
	 * @param usedLimit
	 * @param customerId
	 */
	public CreditCardModel(
			@NotNull(message = "card number cannot be null") @NotBlank(message = "card number cannot be blank") @Pattern(regexp = "[0-9]{16}") String cardNumber,
			@NotNull(message = "card name cannot be null") @NotBlank(message = "card name cannot be blank") CardName cardName,
			@NotNull(message = "card type cannot be null") @NotBlank(message = "card type cannot be blank") CardType cardType,
			@NotNull(message = "card expiry date cannot be null") @Future(message = "Expiry date cannot be in future") LocalDate expiryDate,
			@NotNull(message = "Bank name cannot be null") @NotBlank(message = "Bank name cannot be blank") String bankName,
			@NotNull(message = "cvv cannot be null") @NotBlank(message = "cvv cannot be blank") @Pattern(regexp = "[0-9]{3}") Integer cvv,
			@NotNull(message = "limit cannot be null") @NotBlank(message = "limit cannot be blank") Double cardLimit,
			@NotNull(message = "limit cannot be null") @NotBlank(message = "limit cannot be blank") Double usedLimit,
			@NotNull(message = "customerId cannot be null") @NotBlank(message = "customerId cannot be blank") String customerId) {
		super();
		this.cardNumber = cardNumber;
		this.cardName = cardName;
		this.cardType = cardType;
		this.expiryDate = expiryDate;
		this.bankName = bankName;
		this.cvv = cvv;
		this.cardLimit = cardLimit;
		this.usedLimit = usedLimit;
		this.customerId = customerId;
	}
	

	
	public Double getCardLimit() {
		return cardLimit;
	}


	public void setCardLimit(Double cardLimit) {
		this.cardLimit = cardLimit;
	}


	public Double getUsedLimit() {
		return usedLimit;
	}


	public void setUsedLimit(Double usedLimit) {
		this.usedLimit = usedLimit;
	}


	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public CardName getCardName() {
		return cardName;
	}


	public void setCardName(CardName cardName) {
		this.cardName = cardName;
	}


	public CardType getCardType() {
		return cardType;
	}


	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}


	public LocalDate getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
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
		CreditCardModel other = (CreditCardModel) obj;
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
		if (cvv == null) {
			if (other.cvv != null)
				return false;
		} else if (!cvv.equals(other.cvv))
			return false;
		if (expiryDate == null) {
			if (other.expiryDate != null)
				return false;
		} else if (!expiryDate.equals(other.expiryDate))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return String.format(
				"CreditCardModel [cardNumber=%s, cardName=%s, cardType=%s, expiryDate=%s, bankName=%s, cvv=%s]",
				cardNumber, cardName, cardType, expiryDate, bankName, cvv);
	}

	
    
}
