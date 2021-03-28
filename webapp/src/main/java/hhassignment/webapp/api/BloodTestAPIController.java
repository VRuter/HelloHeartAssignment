package hhassignment.webapp.api;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hhassignment.webapp.data.BloodTestData;
import hhassignment.webapp.dto.DtoBloodTestEvaluationResponse;
import hhassignment.webapp.dto.DtoBloodTestResult;
import hhassignment.webapp.dto.EvaluationResult;
import hhassignment.webapp.logic.BloodTestLogic;
import hhassignment.webapp.logic.TestNameMatcher;

/**
 * API Web Controller for Blood Tests
 */
@RestController
@RequestMapping("/api")
@Validated
public class BloodTestAPIController {
	Logger logger = Logger.getLogger(BloodTestAPIController.class);

	// Data source injection.
	@Autowired
	BloodTestData bloodTestData;

	// Values read from configuration
	@Value("${api-config.blood-test.match-ratio-threshold}")
	Double ratioThreshold;
	@Value("${api-config.blood-test.threshold-property-name}")
	String thresholdPropertyName;

	/**
	 * Exception handler that will catch {@link ConstraintViolationException}
	 * Exceptions thrown for validation errors on the endpoints in the Controller.
	 * 
	 * @param e <code>Exception</code> object.
	 * @return <code>ResponseEntity</code> with a BAD_REQUEST HTTP code and an error
	 *         message detailing the validation errors.
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
		logger.warn(String.format("Request failed validation: %s", e.getMessage()));
		return new ResponseEntity<>(String.format("Invalid data provided: %s", e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	/**
	 * <code>POST</code> endpoint that receives {@link DtoBloodTestResult},
	 * validates it and returns the evaluation response as
	 * {@link DtoBloodTestEvaluationResponse}
	 * 
	 * @param test {@link DtoBloodTestResult} object with the test data.
	 * @return <code>ResponseEntity</code> containing
	 *         {@link DtoBloodTestEvaluationResponse} with the evaluation result or
	 *         a <code>String</code> with an error message.
	 */
	@PostMapping("/evaluateSingleTest")
	public ResponseEntity<?> evaluateSingleTest(@RequestBody @DtoBloodTestResultsFields DtoBloodTestResult test) {
		// SIMULATE NETWORK LATENCY
		try {
			Thread.sleep(400L);
		} catch (InterruptedException e1) {
		}

		List<String> testNames = bloodTestData.getDataSetKeys();
		if (testNames == null) {
			String dataSetNullErrorMessage = "Dataset is null";
			logger.warn(String.format("Request failed: %s", dataSetNullErrorMessage));
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			String matchedTestName = TestNameMatcher.findMatch(testNames, test.getTestName(), ratioThreshold, true);

			boolean matchSuccess;
			EvaluationResult evalResult;

			if (matchedTestName != null) {
				matchSuccess = true;
				evalResult = BloodTestLogic.getEvalResult(
						(Double) bloodTestData.getValueByPropertyName(matchedTestName, thresholdPropertyName),
						(Double) test.getValue());
			} else {
				matchSuccess = false;
				evalResult = EvaluationResult.Unknown;
			}

			return new ResponseEntity<>(new DtoBloodTestEvaluationResponse(matchSuccess, matchedTestName, evalResult),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.warn(String.format("Request failed: %s", e.getMessage()));
			return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
