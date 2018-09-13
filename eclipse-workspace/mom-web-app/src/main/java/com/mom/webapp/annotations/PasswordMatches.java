/**
 * 
 */
package com.mom.webapp.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.mom.webapp.validators.EmailValidator;
import com.mom.webapp.validators.PasswordMatchesValidator;

@Documented
@Retention(RUNTIME)
@Target({ TYPE, ANNOTATION_TYPE })
@Constraint(validatedBy = PasswordMatchesValidator.class)
/**
 * @author Brehima
 *
 */
public @interface PasswordMatches {
	String message() default "{message.signup.password.matches}";
    Class<?>[] groups() default {}; 
    Class<? extends Payload>[] payload() default {};
}
