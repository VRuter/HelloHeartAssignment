package hhassignment.webapp.logic;

import hhassignment.webapp.dto.EvaluationResult;

/**
 * Utilities class with static methods providing various helper methods for the
 * Blood Test API Controller.
 */
public class BloodTestLogic {
	/**
	 * Gets the {@link EvaluationResult} value after comparing the
	 * <code>testValue</code> to <code>threshold</code>.
	 * 
	 * @param threshold <code>Double</code> value of the threshold to compare to.
	 * @param testValue <code>Double</code> value to compare to the threshold.
	 * @return {@link EvaluationResult} <code>Good</code> if <code>testValue</code>
	 *         is beneath the <code>threshold</code>. <code>Bad</code> otherwise.
	 */
	public static EvaluationResult getEvalResult(Double threshold, Double testValue) {
		int compare = testValue.compareTo(threshold);

		// Is testValue less than threshold?
		if (compare < 0) {
			return EvaluationResult.Good;
		} else {
			return EvaluationResult.Bad;
		}
	}

}
