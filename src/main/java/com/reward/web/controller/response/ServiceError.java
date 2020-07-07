package com.reward.web.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The Class ServiceError.
 */
public class ServiceError {

	/** The code. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String code;

	/** The message. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String message;

	public ServiceError(){
	
	}
	
	public ServiceError(String code, String message ){
		this.code=code;
		this.message=message;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "ServiceError{" +
				"code='" + code + '\'' +
				", message='" + message + '\'' +
				'}';
	}
}
