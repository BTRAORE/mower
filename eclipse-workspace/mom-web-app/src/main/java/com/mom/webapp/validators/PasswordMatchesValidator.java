/**
 * 
 */
package com.mom.webapp.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.mom.webapp.annotations.PasswordMatches;
import com.mom.webapp.dto.CustomerDto;

/**
 * @author Brehima
 *
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
    public void initialize(PasswordMatches constraintAnnotation) {       
    } 
	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext arg1) {
		CustomerDto user = (CustomerDto) obj;
        return user.getPassword().equals(user.getConfirmPassword());	
    }

}
