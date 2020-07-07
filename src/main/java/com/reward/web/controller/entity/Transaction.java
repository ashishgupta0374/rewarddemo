package com.reward.web.controller.entity;

import com.reward.web.controller.utils.RewardUtil;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The Class Transaction.
 */
@Entity
@Table(name = "Transaction")
public class Transaction implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The transaction id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TRANSACTION_ID")
	private Long transactionId;

	/** The amount. */
	@Column(name = "AMOUNT")
	private int amount;
	
	/** The amount. */
	@Column(name = "REWARD_POINT")
	private int rewardPoint;

	/** The date. */
	@Column(name = "UPDATE_TS")
	private Date date;
	
	/** The customer. */
	@ManyToOne
	@JoinColumn(name = "EMAIL", nullable = false)
	private Customer customer;

	/**
	 * Instantiates a new transaction.
	 */
	public Transaction() {

	}

	/**
	 * Instantiates a new transaction.
	 *
	 * @param customer the customer
	 * @param amount   the amount
	 * @param date     the date
	 */
	public Transaction(Customer customer, int amount, Date date) {
		this.customer = customer;
		this.amount = amount;
		this.date = date;
		this.rewardPoint= RewardUtil.getReward(amount);
	}

	/**
	 * Gets the transaction id.
	 *
	 * @return the transaction id
	 */
	public Long getTransactionId() {
		return transactionId;
	}

	/**
	 * Sets the transaction id.
	 *
	 * @param transactionId the new transaction id
	 */
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Sets the customer.
	 *
	 * @param customer the new customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public int getRewardPoint() {
		return rewardPoint;
	}
	
	public void setRewardPoint(int rewardPoint) {
		this.rewardPoint = rewardPoint;
	}
}
