package com.arpangroup.inventory.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@JsonPropertyOrder({"errorCode", "error_code", "error", "message"})
public class ErrorMessage implements Serializable {
    private final static long serialVersionUID = -1L;
    protected String error;
    protected String message;

    private ErrorMessage(){
        // private constructor
    }

    public ErrorMessage(String error, String message) {
        this.error = error;
        this.message = message;
    }
}
