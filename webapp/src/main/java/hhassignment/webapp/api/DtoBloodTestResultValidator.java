package hhassignment.webapp.api;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import hhassignment.webapp.dto.DtoBloodTestResult;

/**
 * Validator for {@link DtoBloodTestResult} objects. Checks that all fields are
 * valid for evaluation.
 */
public class DtoBloodTestResultValidator implements ConstraintValidator<DtoBloodTestResultsFields, DtoBloodTestResult> {
	@Override
	public boolean isValid(DtoBloodTestResult value, ConstraintValidatorContext context) {
		boolean isValid = true;

		if (StringUtils.isBlank(value.getTestName())) {
			context.buildConstraintViolationWithTemplate("Invalid field testName").addConstraintViolation();
			isValid = false;
		}

		if (value.getType() == null) {
			context.buildConstraintViolationWithTemplate("Null field type").addConstraintViolation();
			isValid = false;

		}

		if (value.getValue() == null) {
			context.buildConstraintViolationWithTemplate("Null field Value").addConstraintViolation();
			isValid = false;
		}

		return isValid;
	}
}