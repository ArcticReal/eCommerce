package com.skytala.eCommerce.framework.validation;

import com.skytala.eCommerce.service.login.util.RegisterMessages;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE,ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {
    String message() default RegisterMessages.PASSWORDS_NO_MATCH;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
