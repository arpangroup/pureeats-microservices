package com.arpangroup.inventory.validator;

import com.arpangroup.inventory.exception.ValidationException;
import com.arpangroup.inventory.exception.ValidationException.ValidationError;
import org.springframework.stereotype.Component;

public class BaseValidator {
    public int validate(Integer id) throws ValidationException{
        if (id == null || id <= 0) {
            throw new ValidationException(ValidationError.INVALID_ID);
        }
        return id;
    }
}
