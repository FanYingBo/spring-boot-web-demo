package com.study.selfs.gupao.springboot.validation;

import com.study.selfs.gupao.springboot.validation.annotation.NameValidation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * 校验name字段为“gupao”开头的，以数字结尾
 */
public class NameValidationConstraintValidator implements ConstraintValidator<NameValidation,String> {
    @Override
    public void initialize(NameValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //apache
        String[] parts = value.split("_");
        if(ArrayUtils.getLength(parts) != 2){
            return false;
        }
        String prefix = parts[0];
        String suffix = parts[1];
        boolean prefixCheck = Objects.equals(prefix, "gupao");
        boolean alpha = StringUtils.isAlpha(suffix);
        if(prefixCheck && alpha){
            return true;
        }
        //Spring
        return false;
    }
}
