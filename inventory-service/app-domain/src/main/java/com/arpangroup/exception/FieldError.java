package com.arpangroup.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@JsonPropertyOrder({"field", "message"})
@NoArgsConstructor
public class FieldError implements Serializable {
    private final static long serialVersionUID = -1L;

    protected String field;
    protected String message;

    public FieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
