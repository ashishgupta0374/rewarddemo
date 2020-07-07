package com.reward.web.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;


/**
 * The Class RewardResponse.
 *
 * @param <T> the generic type
 */
public class RewardResponse<T> implements Serializable{
    
	private static final long serialVersionUID = 1L;

	/** The status. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String status;
    
    /** The request id. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String requestId;
    
    /** The meta. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> meta;
    
    /** The result. */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Optional<T> result;
    
    /** The error. */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Optional<ServiceError> error;
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Gets the request id.
	 *
	 * @return the requestId
	 */
	public String getRequestId() {
		return requestId;
	}
	
	/**
	 * Sets the request id.
	 *
	 * @param requestId the requestId to set
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	/**
	 * Gets the meta.
	 *
	 * @return the meta
	 */
	public Map<String, Object> getMeta() {
		return meta;
	}
	
	/**
	 * Sets the meta.
	 *
	 * @param meta the meta to set
	 */
	public void setMeta(Map<String, Object> meta) {
		this.meta = meta;
	}
	
	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public Optional<T> getResult() {
		return result;
	}
	
	/**
	 * Sets the result.
	 *
	 * @param result the result to set
	 */
	public void setResult(Optional<T> result) {
		this.result = result;
	}
	
	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public Optional<ServiceError> getError() {
		return error;
	}
	
	/**
	 * Sets the error.
	 *
	 * @param error the error to set
	 */
	public void setError(Optional<ServiceError> error) {
		this.error = error;
	}
	
	@Override
	public String toString() {
		return "RewardResponse{" +
				"status='" + status + '\'' +
				", requestId='" + requestId + '\'' +
				", meta=" + meta +
				", result=" + result +
				", error=" + error +
				'}';
	}
}
