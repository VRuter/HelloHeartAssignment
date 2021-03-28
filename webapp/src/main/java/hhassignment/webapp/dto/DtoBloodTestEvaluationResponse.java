package hhassignment.webapp.dto;

/**
 * Simple DTO Class to be used as a return value in the HTTP Response in
 * {@link BloodTestAPIController}.
 */
public class DtoBloodTestEvaluationResponse {
	private Boolean matchSuccess;
	private String officialName;
	private EvaluationResult evaluationResult;

	public DtoBloodTestEvaluationResponse() {
	}

	public DtoBloodTestEvaluationResponse(Boolean matchSuccess, String officialName,
			EvaluationResult evaluationResult) {
		this.matchSuccess = matchSuccess;
		this.officialName = officialName;
		this.evaluationResult = evaluationResult;
	}

	public Boolean getMatchSuccess() {
		return matchSuccess;
	}

	public void setMatchSuccess(Boolean matchSuccess) {
		this.matchSuccess = matchSuccess;
	}

	public String getOfficialName() {
		return officialName;
	}

	public void setOfficialName(String officialName) {
		this.officialName = officialName;
	}

	public EvaluationResult getEvaluationResult() {
		return evaluationResult;
	}

	public void setEvaluationResult(EvaluationResult evaluationResult) {
		this.evaluationResult = evaluationResult;
	}
}
