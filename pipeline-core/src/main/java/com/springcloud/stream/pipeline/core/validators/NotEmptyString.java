package com.springcloud.stream.pipeline.core.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Marks a string as validated according to the following rules:
 * <ul>
 * <li>The String must not be null</li>
 * <li>The String must not be empty</li>
 * <li>The String must contain at least one non-whitespace character</li>
 * </ul>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptyStringValidator.class)
public @interface NotEmptyString {
    /**
     * Allows to specify different behaviors for different groups.
     *
     * @return the given groups
     */
    Class<?>[] groups() default {};

    /**
     * Creates the message to be shown if the Validation associated with this annotation fails.
     *
     * @return an error message
     */
    String message() default "String must not be empty, null or consist of only whitespaces!";

    /**
     * Used to pass metadata to the annotation handler.
     *
     * @return all metadata fields
     */
    Class<? extends Payload>[] payload() default {};
}
