package com.reward.web.controller.exception;

/**
 * The Class ValidationException.
 * 
 * Validation exception  thrown fore invalid input provided
 */
public class ValidationException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The resource name. */
	private String resourceName;
    
    /** The field name. */
    private String fieldName;
    
    /**
     * Instantiates a new validation exception.
     *
     * @param resourceName the resource name
     * @param fieldName the field name
     */
    public ValidationException(String resourceName, String fieldName) {
        super(String.format("%s Invalid input : '%s'", resourceName, fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        
    }
    
    /**
     * Instantiates a new validation exception.
     *
     * @param errorMessage the error message
     */
    public ValidationException(String errorMessage) {
        super(errorMessage);
    }
    
    /**
     * Gets the resource name.
     *
     * @return the resource name
     */
    public String getResourceName() {
        return resourceName;
    }
    
    /**
     * Sets the resource name.
     *
     * @param resourceName the new resource name
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    
    /**
     * Gets the field name.
     *
     * @return the field name
     */
    public String getFieldName() {
        return fieldName;
    }
    
    /**
     * Sets the field name.
     *
     * @param fieldName the new field name
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
