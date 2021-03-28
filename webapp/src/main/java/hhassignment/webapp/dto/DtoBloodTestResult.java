package hhassignment.webapp.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * Abstract Class to be used as a base class for request objects made to
 * {@link BloodTestAPIController}. This extending Classes must override the
 * <code>getValue</code> method with the proper type.
 */
@JsonTypeInfo(use = Id.NAME, include = As.EXISTING_PROPERTY, visible = true, property = "type")
@JsonSubTypes({ @Type(value = DtoNumericBloodTestResult.class, name = BloodTestTypeConstants.NUMERIC) })
public abstract class DtoBloodTestResult {
	private String testName;
	private BloodTestResultType type;

	public DtoBloodTestResult() {

	}

	public DtoBloodTestResult(String testName, BloodTestResultType type) {
		this.testName = testName;
		this.type = type;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public BloodTestResultType getType() {
		return type;
	}

	public void setType(BloodTestResultType type) {
		this.type = type;
	}

	public abstract Object getValue();
}
