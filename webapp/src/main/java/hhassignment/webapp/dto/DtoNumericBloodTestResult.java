package hhassignment.webapp.dto;

/**
 * Extending Class for {@link DtoBloodTestResult} to be used for request objects
 * made to {@link BloodTestAPIController}. This extending Class implements a
 * numeric value for the tests.
 */
public class DtoNumericBloodTestResult extends DtoBloodTestResult {
	private Double value;

	public DtoNumericBloodTestResult() {
		super();
	}

	public DtoNumericBloodTestResult(String testName, BloodTestResultType type, Double value) {
		super(testName, type);
		this.value = value;
	}

	@Override
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}