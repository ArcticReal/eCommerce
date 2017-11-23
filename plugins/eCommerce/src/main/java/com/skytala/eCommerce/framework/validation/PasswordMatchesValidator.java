package com.skytala.eCommerce.framework.validation;

import com.skytala.eCommerce.service.login.dto.UserDetailsDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserDetailsDTO>{

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserDetailsDTO userDetailsDTO, ConstraintValidatorContext context) {
        return userDetailsDTO.getCurrentPassword().equals(userDetailsDTO.getPasswordRetype());
    }
}
