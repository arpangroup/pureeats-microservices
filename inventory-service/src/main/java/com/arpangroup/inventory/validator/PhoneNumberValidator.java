package com.arpangroup.inventory.validator;

import com.arpangroup.inventory.validator.annotation.PhoneNumberConstraint;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint, String> {
    private int minLength;
    private int maxLength;
    private boolean isNullable;
    private boolean isRequired;
    private String pattern;


    @Override
    public void initialize(PhoneNumberConstraint constraintAnnotation) {
        this.minLength = constraintAnnotation.minLength();
        this.maxLength = constraintAnnotation.maxLength();
        this.isNullable = constraintAnnotation.nullable();
        this.isNullable = constraintAnnotation.required();
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        log.info("Inside isValid() :: phone={}: ", phone);
        if (phone != null) {
            if (isValidLength(phone)){
                if (StringUtils.isNotEmpty(pattern)){
                    return matchPhoneNumber(phone, pattern);
                }else {
                    log.error("pattern is empty");
                    return false;
                }
            }else {// length not satisfied
                return false;
            }
        }else{// phone is null
            return isNullable;
        }
    }

    private boolean isValidLength(String phone) {
        boolean isValidLength = phone.length() >= minLength && phone.length() <= maxLength;
        log.info("isValidLength: {}", isValidLength);
        return isValidLength;
    }


    private boolean matchPhoneNumber(String phone, String pattern){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(phone);
        final boolean isMatched =  (m.matches());
        log.info("matchPhoneNumber: {}", isMatched);
        return isMatched;
    }


}
