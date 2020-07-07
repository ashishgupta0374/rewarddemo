package com.reward.web.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * The Class RewardDTO.
 */
public class RewardDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The email. */
	@JsonProperty(value = "EMAIL")
	@NotNull
	private String email;

	/** The month. */
	@JsonProperty(value = "MONTHS")
	@NotNull
	private int months;

	/** The days. */
	@JsonProperty(value = "DAYS")
	@NotNull
	private int days;

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
	 * Gets the months.
	 *
	 * @return the months
	 */
	public int getMonths() {
		return months;
	}

	/**
	 * Sets the months.
	 *
	 * @param months the new months
	 */
	public void setMonths(int months) {
		this.months = months;
	}

	/**
	 * @return the days
	 */
	public int getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public void setDays(int days) {
		this.days = days;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "RewardDTO{" +
				"email='" + email + '\'' +
				", months=" + months +
				", days=" + days +
				'}';
	}
}
