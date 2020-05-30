package com.springcloud.stream.pipeline.core.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.logging.log4j.util.Strings;

/**
 * Checks if a String can be considered "empty", i.e. validation fails if
 * <ul>
 * <li>the variable is {@code null}</li>
 * <li>the variable is the empty {@code String} {@code ""}</li>
 * <li>the variable contains only whitespace characters (as defined with {@link String#trim})</li>
 * </ul>
 */
public class NotEmptyStringValidator implements ConstraintValidator<NotEmptyString, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Strings.isNotBlank(value);
    }

    @Override
    public void initialize(NotEmptyString constraintAnnotation) {
        // Nothing to do
    }
}
