package hhassignment.webapp.dto;

/**
 * Enum containing the available Evaluation values for comparing blood test
 * values to their threshold.
 */
public enum EvaluationResult {
	Good("Good"), Bad("Bad"), Unknown("Unknown");

	private String evalResult;

	EvaluationResult(String evalResult) {
		this.evalResult = evalResult;
	}

	public String getEvaluationResult() {
		return evalResult;
	}
}
