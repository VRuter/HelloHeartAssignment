package hhassignment.webapp.api;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Constraint validator annotation for {@link DtoBloodTestResult}
 */
@Documented
@Constraint(validatedBy = DtoBloodTestResultValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD, PARAMETER })
public @interface DtoBloodTestResultsFields {
	String message() default "DtoBloodTestResult object is invalid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}