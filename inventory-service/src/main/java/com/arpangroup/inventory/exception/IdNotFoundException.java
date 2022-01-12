package com.arpangroup.inventory.exception;

public class IdNotFoundException extends BaseException {
    public IdNotFoundException() {
        super(ExceptionReason.ID_NOT_FOUND);
    }

    public IdNotFoundException(String message) {
        super(ExceptionReason.ID_NOT_FOUND.value(), message);
    }
}
