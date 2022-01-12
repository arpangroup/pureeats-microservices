package com.arpangroup.inventory.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.joda.time.DateTime;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@JsonPropertyOrder({"timestamp", "status", "path", "is_authenticated", "message", "data"})
public class ApiError {
    private final Long timestamp = new Date().getTime();
    private final String path;
    private final List<ErrorMessage> errors = new ArrayList<>();


    public ApiError(List<ErrorMessage> errors, WebRequest request) {
        this.errors.addAll(errors);
        this.path = request != null ? request.getDescription(false).replace("uri=", "") : null;
    }

    public ApiError(String errorCode, String errorMessage, WebRequest request) {
        this.errors.add(new ErrorMessage(errorCode, errorMessage));
        this.path = request != null ? request.getDescription(false).replace("uri=", "") : null;
    }

    public ApiError(ErrorMessage errorMessage, WebRequest request) {
        this.errors.add(errorMessage);
        this.path = request != null ? request.getDescription(false).replace("uri=", "") : null;
    }

}


