package com.arpangroup.onboarding.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@JsonPropertyOrder({"status", "error", "message", "path", "timeStamp"})
public class ApiError {
    private final Long timestamp = new Date().getTime();

    private final HttpStatus status;
    private final String error;
    private final String message;
    private final String path;
    private Date timeStamp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<FieldError> fields;

    public ApiError(String error, String message, List<FieldError> fields, WebRequest request, HttpStatus httpStatus){
        this.error = error;
        this.message = message;
        this.fields = fields;
        this.path = request != null ? request.getDescription(false).replace("uri=", "") : null;
//        this.status = httpStatus.getReasonPhrase();
        this.status = httpStatus;
        this.timeStamp = new Date();
    }


    public ApiError(String error, String message, WebRequest request, HttpStatus status) {
        this(error, message, null, request, status);
    }
    public ApiError(@NotNull ErrorType errorType, @NotNull List<FieldError> errors, WebRequest request, HttpStatus status) {
        this(errorType.value(), errorType.getReasonPhrase(), errors, request, status);
    }

    public enum ErrorType{
        MethodArgumentNotValidException("V001", "MethodArgumentNotValid"),
        BindException("V002", "BindingError!!, invalid request body"),
        HttpMessageNotReadableException("V003", "HttpMessageNotReadable!!, invalid request body");


        private final String value;
        private final String reasonPhrase;

        ErrorType(String value, String reasonPhrase) {
            this.value = value;
            this.reasonPhrase = reasonPhrase;
        }

        public String value() {
            return value;
        }

        public String getReasonPhrase() {
            return this.reasonPhrase;
        }
    }



    private String camelToSnake(String str){
        // Empty String
        String result = "";

        // Append first character(in lower case)
        // to result string
        char c = str.charAt(0);
        result = result + Character.toLowerCase(c);

        // Traverse the string from
        // ist index to last index
        for (int i = 1; i < str.length(); i++) {

            char ch = str.charAt(i);

            // Check if the character is upper case
            // then append '_' and such character
            // (in lower case) to result string
            if (Character.isUpperCase(ch)) {
                result = result + '_';
                result
                        = result
                        + Character.toLowerCase(ch);
            }

            // If the character is lower case then
            // add such character into result string
            else {
                result = result + ch;
            }
        }

        // return the result
        return result;
    }

}


