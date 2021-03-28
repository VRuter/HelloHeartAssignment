package hhassignment.webapp.dto;

/**
 * Enum containing the {@link DtoBloodTestResult} subclass types available. Used
 * to supplement the {@link DtoBloodTestResult} base Class to determine which
 * subtype should be used.
 */
public enum BloodTestResultType {
	numeric(BloodTestTypeConstants.NUMERIC);

	private String resultType;

	BloodTestResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getBloodTestResultType() {
		return resultType;
	}
}
