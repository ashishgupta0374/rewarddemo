package com.reward.web.controller.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.reward.web.controller.utils.RewardUtil;

import javax.validation.constraints.NotNull;


/**
 * The Class TransactionDTO.
 */
public class TransactionDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The customer ID. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonProperty(value="EMAIL")
	@NotNull
	private String email;

	/** The amount. */
	@JsonProperty(value="AMOUNT")
	@NotNull
	private int amount;

	/** The reward. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonProperty(value="REWARD_POINT")
	private int reward;

	/** The transactionDate. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonProperty(value="TRANSACTION_DATE")
	private String transactionDate;

	/**
	 * Instantiates a new transaction DTO.
	 */
	public TransactionDTO() {

	}

	/**
	 * Instantiates a new transaction DTO.
	 *
	 * @param amount the amount
	 * @param transactionDate  the transactionDate
	 */
	public TransactionDTO(int amount, int reward, Date transactionDate) {
		this.amount = amount;
		this.reward = reward;
		this.transactionDate = RewardUtil.getFormattedDate(transactionDate);
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * Gets the reward.
	 *
	 * @return the reward
	 */
	public int getReward() {
		return reward;
	}

	/**
	 * Sets the reward.
	 *
	 * @param reward the new reward
	 */
	public void setReward(int reward) {
		this.reward = reward;
	}
	
	/**
	 * Gets the transaction date.
	 *
	 * @return the transaction date
	 */
	public String getTransactionDate() {
		return transactionDate;
	}
	
	/**
	 * Sets the transaction date.
	 *
	 * @param transactionDate the new transaction date
	 */
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	@Override
	public String toString() {
		return "TransactionDTO{" +
				"email='" + email + '\'' +
				", amount=" + amount +
				", reward=" + reward +
				", transactionDate='" + transactionDate + '\'' +
				'}';
	}
}
