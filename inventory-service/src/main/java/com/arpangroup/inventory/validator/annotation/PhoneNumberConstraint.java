package com.arpangroup.inventory.validator.annotation;


import com.arpangroup.inventory.validator.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumberConstraint {
    String message() default "{validation.constraints.phone.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int minLength() default 10;
    int maxLength() default 12;
    boolean nullable() default false;
    boolean required() default false;


    /**
     * The first digit should contain numbers between 6 and 9.
     * The rest 9 digits can contain any number between 0 and 9.
     * The mobile number can have 11 digits also by including 0 at the starting.
     * The mobile number can be of 12 digits also by including 91 at the starting.
     *
     * Input  : Enter Mobile Number: 7873923408
     * Output : Valid Mobile Number
     * See : ttps://www.geeksforgeeks.org/java-program-to-check-for-a-valid-mobile-number/
     */
    String pattern() default "^\\d{10}$";
}
