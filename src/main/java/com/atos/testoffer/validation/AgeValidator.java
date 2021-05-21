package com.atos.testoffer.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validate the age of the user, do not register a user with age < 18
 */
public class AgeValidator implements ConstraintValidator<ValidAge, Integer> {
    private static final int MIN_AGE = 18;

    @Override
    public boolean isValid(final Integer age, final ConstraintValidatorContext context) {
        return age != null && age >= MIN_AGE;
    }
}
