package main.java.validator.constraintAnnotations;

import main.java.validator.EmailValidator;

import javax.validation.Constraint;

@Constraint(validatedBy = EmailValidator.class)
public @interface ValidEmail {
    String message() default "Invalid email";
}
