package com.reward.web.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * The Class CustomerDTO.
 */
public class CustomerDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** The email. */
	@JsonProperty(value="EMAIL")
	@NotNull
	private String email;

	/** The first name. */
	@JsonProperty(value="FIRST_NAME")
	@NotNull
	private String firstName;

	/** The last name. */
	@JsonProperty(value="LAST_NAME")
	@NotNull
	private String lastName;

	/** The transactions. */
	@JsonProperty(value="TRANSACTIONS")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<TransactionDTO> transactions = new HashSet<>();

	/** The total reward. */
	@JsonProperty(value="TOTAL_REWARD")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer totalReward;

	/**
	 * Instantiates a new customer DTO.
	 */
	public CustomerDTO() {

	}

	/**
	 * Instantiates a new customer DTO.
	 *
	 * @param email the customer email
	 * @param firstName  the first name
	 * @param lastName   the last name
	 */
	public CustomerDTO(String email, String firstName, String lastName) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	
	/**
	 * Gets the first name.
	 *
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the transactions.
	 *
	 * @return the transactions
	 */
	public Set<TransactionDTO> getTransactions() {
		return transactions;
	}

	/**
	 * Sets the transactions.
	 *
	 * @param transactions the transactions to set
	 */
	public void setTransactions(Set<TransactionDTO> transactions) {
		this.transactions = transactions;
	}

	/**
	 * Gets the total reward.
	 *
	 * @return the totalReward
	 */
	public Integer getTotalReward() {
		return totalReward;
	}

	/**
	 * Sets the total reward.
	 *
	 * @param totalReward the totalReward to set
	 */
	public void setTotalReward(Integer totalReward) {
		this.totalReward = totalReward;
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
	
	@Override
	public String toString() {
		return "CustomerDTO{" +
				"email='" + email + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", transactions=" + transactions +
				", totalReward=" + totalReward +
				'}';
	}
}
