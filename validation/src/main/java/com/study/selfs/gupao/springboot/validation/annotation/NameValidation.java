package com.study.selfs.gupao.springboot.validation.annotation;

import com.study.selfs.gupao.springboot.validation.NameValidationConstraintValidator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { NameValidationConstraintValidator.class })
public @interface NameValidation {

}
