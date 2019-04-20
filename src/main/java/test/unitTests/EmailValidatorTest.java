package unitTests;

import main.java.validator.EmailValidator;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.validation.constraints.AssertFalse;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailValidatorTest {

    @Mock
    ConstraintValidatorContextImpl constraintValidatorContext;

    @Test
    void shouldReturnFalseForEmailWithoutAt() {

        //given
        final String emailWithoutAt = "qwerqwer.qwerqwr.com";
        EmailValidator validator = new EmailValidator();

        //when
        boolean result = validator.isValid(emailWithoutAt, constraintValidatorContext);

        //then
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueForProperlyFormattedEmail() {

        //given
        final String properlyFormattedEmail = "asdf.wert@tert.com";
        EmailValidator validator = new EmailValidator();

        //when
        boolean result = validator.isValid(properlyFormattedEmail, constraintValidatorContext);

        //then
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseForEmptyString() {

        //given
        final String emptyStringEmail = "";
        EmailValidator validator = new EmailValidator();

        //when
        boolean result = validator.isValid(emptyStringEmail, constraintValidatorContext);

        //then
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseForNullValue() {

        //given
        final String nullValueEmail = null;
        EmailValidator validator = new EmailValidator();

        //when
        boolean result = validator.isValid(nullValueEmail, constraintValidatorContext);

        //then
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseForEmailWithTwoAts() {

        //given
        final String emailWithTwoAts =  "asdf.wert@tert@fsdf.com";
        EmailValidator validator = new EmailValidator();

        //when
        boolean result = validator.isValid(emailWithTwoAts, constraintValidatorContext);

        //then
        assertFalse(result);
    }


    @Test
    void shouldReturnFalseForEmailWithDisallowedCharacters() {

        //given
        final String emailWithDisallowedCharacters =  "asd*.wert@te#t$fsdf.com";
        EmailValidator validator = new EmailValidator();

        //when
        boolean result = validator.isValid(emailWithDisallowedCharacters, constraintValidatorContext);

        //then
        assertFalse(result);
    }

}