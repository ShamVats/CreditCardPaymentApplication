package com.cg.creditcardpayment.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="statements")
public class StatementEntity {	
	
	@Id
	@Column(name="statement_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long statementId;
	
	@Column(name="due_amount",nullable=false)
	private Double dueAmount;
	
	@Column(name="bill_amount",nullable=false)
	private Double billAmount;
	
	@Column(name="bill_date",nullable=false)
	private LocalDate billDate;
	
	@Column(name="due_date",nullable=false)
	private LocalDate dueDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="card_number")
	private CreditCardEntity creditCard;
	
	@Column(name="customer_name",nullable=false)
	private String customerName;
	
	public StatementEntity() {
		/*Default Constructor*/
	}
	
	public StatementEntity(Long statementId, Double billAmount, Double dueAmount, LocalDate billDate, LocalDate dueDate,CreditCardEntity creditCard) {
		super();
		this.statementId=statementId;
		this.billAmount = billAmount;
		this.dueAmount=dueAmount;
		this.billDate = billDate;
		this.dueDate = dueDate;
		this.creditCard=creditCard;
		this.customerName=creditCard.getCustomer().getName();
	}


	public Double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
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


	public CreditCardEntity getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCardEntity creditCard) {
		this.creditCard = creditCard;
	}

	
	public String getCustomerName() {
		return creditCard.getCustomer().getName();
	}

	public void setCustomerName(String customerName) {
		if(creditCard.getCustomer().getName().equals(customerName)) {
			this.customerName=customerName;
		}else {
			this.customerName = creditCard.getCustomer().getName();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billAmount == null) ? 0 : billAmount.hashCode());
		result = prime * result + ((billDate == null) ? 0 : billDate.hashCode());
		result = prime * result + ((creditCard == null) ? 0 : creditCard.hashCode());
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
		StatementEntity other = (StatementEntity) obj;
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
		if (creditCard == null) {
			if (other.creditCard != null)
				return false;
		} else if (!creditCard.equals(other.creditCard))
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
		return String.format("Statement [statementId=%s, dueAmount=%s, billDate=%s, dueDate=%s,creditCard =%s customer Name=%s]",
				statementId, dueAmount, billDate, dueDate,creditCard,creditCard.getCustomer().getName());
	}
	
	
}
